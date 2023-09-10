#!/bin/bash

file="oppo.log"
cp ${file} ${file}.`date +%y%m%d%H%M`
while read line
do
    id=${line}

    echo "##################################################"
    echo -e "\033[32m (info) git cherry-pick $id \033[0m"
    git cherry-pick $id
    if [ $? -ne 0 ] ;then
        echo -e "\033[31m (error): can't cherry-pick ${id}!!  \033[0m"
        break
    else
        echo -e "\033[32m (info): ${id} cherry-pick successful \033[0m"
        sed -i '/'${line}'/d' ${file}
    fi
done < ${file}
