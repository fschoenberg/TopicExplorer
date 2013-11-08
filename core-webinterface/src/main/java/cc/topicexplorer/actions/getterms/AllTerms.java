package cc.topicexplorer.actions.getterms;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;
import cc.topicexplorer.database.Database;
import cc.topicexplorer.database.SelectMap;

import com.google.common.util.concurrent.UncheckedExecutionException;

public final class AllTerms {
	private PrintWriter outWriter;
	private Database database;
	private SelectMap databaseQuery;

	public AllTerms(Database db, PrintWriter out) {
		setDatabase(db);
		setServletWriter(out);
		this.databaseQuery = new SelectMap();
		List<String> columnNames_neededForCore = new ArrayList<String>(Arrays.asList("TERM_ID", "DOCUMENT_FREQUENCY",
				"CORPUS_FREQUENCY", "INVERSE_DOCUMENT_FREQUENCY", "CF_IDF"));
		for (String columnName : columnNames_neededForCore) {
			this.addTableColumn(columnName, columnName);
		}
		this.databaseQuery.from.add("TERM");
	}

	public void setServletWriter(PrintWriter out) {
		this.outWriter = out;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public void addTableColumn(String tableColumn, String tableColumnName) {
		this.databaseQuery.select.add(tableColumn + " as " + tableColumnName);
	}

	public void executeQueriesAndWriteAllTerms() throws SQLException {
		if (this.database == null) {
			throw new UncheckedExecutionException(new IllegalStateException("Database has not been set, yet."));
		}
		if (this.outWriter == null) {
			throw new UncheckedExecutionException(new IllegalStateException("PrintStream has not been set, yet."));
		}

		List<String> columnNames = new ArrayList<String>();
		JSONObject rowsWithIndex = new JSONObject();
		JSONObject row = new JSONObject();

		ResultSet resultSet = database.executeQuery(this.databaseQuery.getSQLString());

		for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
			columnNames.add(resultSet.getMetaData().getColumnName(i + 1));
		}
		while (resultSet.next()) {
			for (String columnName : columnNames) {
				row.put(columnName, resultSet.getObject(columnName));
			}
			rowsWithIndex.put(resultSet.getObject("TERM_ID"), row);
			row.clear();
		}
		this.outWriter.print(rowsWithIndex);
	}
}