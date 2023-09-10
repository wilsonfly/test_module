
cmdOut="rm out -rf"
cmdRepo="rm .repo -rf"
cmdFiles="rm ./* -rf"
procNum=50

showUsage()
{
    echo "Usage:"
    echo "[${0##*/} out]:  rm out -rf"
    echo "[${0##*/} repo]: rm .repo -rf"
    echo "[${0##*/} files]: rm ./* -rf"
    echo "[${0##*/} all]:  rm out -rf;rm .repo -rf;rm ./* -rf"
}

func_cleanOut()
{
    for ((i=1;i<$procNum;i++))
    do
        echo "$i:"$cmdOut
        $cmdOut &
    done
}

func_cleanRepo()
{
    for ((i=1;i<$procNum;i++))
    do
        echo "$i:"$cmdRepo
        $cmdRepo &
    done
}

func_cleanFiles()
{
    for ((i=1;i<$procNum;i++))
    do
        #echo "$i:"$cmdFiles
        $cmdFiles &
    done
}


func_cleanAll()
{
    func_cleanOut
    func_cleanRepo
    func_cleanFiles
}

echo "pwd:`pwd`"
cmd=$1
case "$cmd" in
    "out")
        func_cleanOut
        ;;
    "repo")
        func_cleanRepo
        ;;
    "files")
        func_cleanFiles
        ;;
    "all")
        func_cleanAll
        ;;
    *)
        showUsage
        ;;
esac

