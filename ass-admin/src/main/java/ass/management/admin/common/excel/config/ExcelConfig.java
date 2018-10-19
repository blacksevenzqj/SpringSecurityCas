/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.config;

public interface ExcelConfig {

    public interface Convert {

        public static final String DEFAULT_KEY = "defaultKey";

        public static final String ORG_CODE = "orgCode";

        public static final String DCDB_INFO_ID = "dcdbInfoId";

        public static final String ZRUNIT_NAME = "zrunitname";

        public static final String XBUNIT_NAME = "xbunitname";

    }

    public interface Bean {

        public static final String DCDB_GZMBFJ_AO = "dcdbgzmbfjAO";

        public static final String DB_XITEM_AO = "dbxitemAO";

        public static final String DCDB_KF_ITEM_AO = "dcdbFkInfoItemAO";
    }

}
