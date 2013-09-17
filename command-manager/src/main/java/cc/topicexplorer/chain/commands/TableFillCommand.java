package cc.topicexplorer.chain.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.chain.Context;
import org.jooq.impl.Factory;

public abstract class TableFillCommand extends TableCommand{

	@Override
	public void tableExecute(Context context) {
		try {
			fillTable();
		//	analyseTable();
		//	optimizeTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void analyseTable() throws SQLException {
		database.analyseTable(this.tableName);
	}

	public void optimizeTable() throws SQLException {
		database.optimizeTable(this.tableName);
	}

	public abstract void fillTable() throws SQLException;
}