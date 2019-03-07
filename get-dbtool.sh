#!/usr/bin/env bash
if [ -f choerodon-tool-liquibase.jar ]; then
    echo "dbtool exists,do you want to re-download ? (y/n)"
    read cmd
    if [ $cmd = "y" ] 
    then
        rm choerodon-tool-liquibase.jar  
    else 
        exit 0
    fi
fi
url=https://nexus.choerodon.com.cn/repository/choerodon-release/io/choerodon/choerodon-tool-liquibase/0.9.3.RELEASE/choerodon-tool-liquibase-0.9.3.RELEASE.jar
curl $url -o choerodon-tool-liquibase.jar
