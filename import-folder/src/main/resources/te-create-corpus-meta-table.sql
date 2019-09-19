CREATE TABLE CORPUS_${corpus} (
    DOCUMENT_ID    INTEGER(11) NOT NULL,
    TITLE          VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL,
    URL            VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL,
    DOCUMENT_DATE  DATETIME NOT NULL,
    CONSTRAINT DOCUMENT_ID_PK PRIMARY KEY ( DOCUMENT_ID )
    )
    ENGINE = ARIA DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin,
    TRANSACTIONAL = 1,
    COMMENT = 'meta data of corpus'
;