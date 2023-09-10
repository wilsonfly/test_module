
root=`pwd`
target=$1
#echo $root

echo "=========All branches========="
echo -e "\t 1) pisces-r-9216"
echo -e "\t 2) pisces-r-9216_XD280"
echo -e "\t 3) pisces-r-9216-1.6_20210924_GTVS"
echo -e "\t 4) pisces-r-9216-1.10_20211106_MP"
echo -e "\t 5) pisces-r-9216-1.12_20211211_OTA1"
echo -e "\t 6) pisces-r-9216_XD280-1.2_20220214_MP"
echo -e "\t 7) pisces-r-9216-1.14_20220709_OTA2"
echo -e "\t 8) aquarius-q-9612"
echo -e "\t 9) aquarius-q-9612-1.10_20210725_OTA2"
echo -e "\t10) aquarius-q-9612-1.12_20220517_OTA3"
echo -e "\t11) aquarius-q-9612-1.14_20230303_OTA4"
echo -e "\t12) capricorn-p-9652"
echo -e "\t13) capricorn-p-9652-1.10_20201217"
echo -e "\t14) capricorn-p-9652-1.18_20211001_OTA3"
echo -e "\t15) capricorn-p-9652-1.10_20220209_OTA5"
echo -e "\t16) capricorn-p-9652-1.18_20220216_K9s_MP"
echo -e "\t17) capricorn-p-9652-1.18_20220106_Netflix"
echo -e "\t18) capricorn-p-9652-1.20_20220609_OTA4"
echo -e "\t19) capricorn-p-9652-1.6_20201029  -->会议机"
echo -e "\t20) sagittarius-p-9950"
echo -e "\t21) sagittarius-p-9950-1.4_20200904      -->MP分支"
echo -e "\t22) sagittarius-p-9950-1.4_20200928      -->ota1分支"
echo -e "\t23) sagittarius-p-9950-1.8_20210611_OTA4"
echo -e "\t24) sagittarius-p-9950-1.10_20220305_OTA5"
echo -e "\t25) aries-r-9617"
echo -e "\t26) aries-r-9617-final"
echo -e "\t27) aries-r-9617-final-GTVS"
echo -e "\t28) aries-r-9617-final-MP"
echo -e "\t29) aries-r-9617-final-Netflix"
echo -e "\t30) aries-r-9617-final-1.4_20221226_OTA1"
echo -e "\t31) scorpio-p-5670"
echo -e "\t32) scorpio-p-5670-1.20_20210520_OTA8"

echo -e "===========>input your choice:"

read -n 3 optionBranch

ln -s ~/data/shell/platform_init_and_sync_svr.sh
ln -s ~/data/shell/buildTargetSys.sh

function download()
{
    echo "will download ====>$1"
    sleep 2
    ./platform_init_and_sync_svr.sh $1
    repo start $1 --all
}

case "$optionBranch" in
    1)
        download pisces-r-9216
        ;;
    2)
        download pisces-r-9216_XD280
        ;;
    3)
        download pisces-r-9216-1.6_20210924_GTVS
        ;;
    4)
        download pisces-r-9216-1.10_20211106_MP
        ;;
    5)
        download pisces-r-9216-1.12_20211211_OTA1
        ;;
    6)
        download pisces-r-9216_XD280-1.2_20220214_MP
        ;;
    7)
        download pisces-r-9216-1.14_20220709_OTA2
        ;;
    8)
        download aquarius-q-9612
        ;;
    9)
        download aquarius-q-9612-1.10_20210725_OTA2
        ;;
    10)
        download aquarius-q-9612-1.12_20220517_OTA3
        ;;
    11)
        download aquarius-q-9612-1.14_20230303_OTA4
        ;;
    12)
        download capricorn-p-9652
        ;;
    13)
        download capricorn-p-9652-1.10_20201217
        ;;
    14)
        download capricorn-p-9652-1.18_20211001_OTA3
        ;;
    15)
        download capricorn-p-9652-1.10_20220209_OTA5
        ;;
    16)
        download capricorn-p-9652-1.18_20220216_K9s_MP
        ;;
    17)
        download capricorn-p-9652-1.18_20220106_Netflix
        ;;
    18)
        download capricorn-p-9652-1.20_20220609_OTA4
        ;;
    19)
        download capricorn-p-9652-1.6_20201029
        ;;
    20)
        download sagittarius-p-9950
        ;;
    21)
        download sagittarius-p-9950-1.4_20200904
        ;;
    22)
        download sagittarius-p-9950-1.4_20200928
        ;;
    23)
        download sagittarius-p-9950-1.8_20210611_OTA4
        ;;
    24)
        download sagittarius-p-9950-1.10_20220305_OTA5
        ;;
    25)
        download aries-r-9617
        ;;
    26)
        download aries-r-9617-final
        ;;
    27)
        download aries-r-9617-final-GTVS
        ;;
    28)
        download aries-r-9617-final-MP
        ;;
    29)
        download aries-r-9617-final-Netflix
        ;;
    30)
        download aries-r-9617-final-1.4_20221226_OTA1
        ;;
    31)
      download scorpio-p-5670
      ;;
    32)
        download scorpio-p-5670-1.20_20210520_OTA8
        ;;
    *)
        echo "unknown target"
        ;;
esac

