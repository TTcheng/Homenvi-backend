#!/bin/bash
read -t 30 -p "which database?：" database

if [[ -n "$database" ]]; then
	java -Dspring.datasource.url="jdbc:sqlserver://10.211.109.102:1433;DatabaseName=srm_order" \
    	 -Dspring.datasource.username=SA \
    	 -Dspring.datasource.password=123siyali \
    	 -Ddata.drop=false -Ddata.init=init \
    	 -Ddata.dir=src/main/resources/script/db/finance \
    	 -jar target/choerodon-tool-liquibase.jar
else
	echo "none database choose"
fi
exit 0
