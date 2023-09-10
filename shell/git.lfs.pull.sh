#!/bin/bash
#git remote set-url oneplus.tv ssh://tvgerrit.oneplus.cn/mtk/lfs/mtk_obj
#git lfs pull

cmd1="git remote set-url oneplus.tv ssh://tvgerrit.oneplus.cn/mtk/lfs/mtk_obj"
cmd2="git lfs pull"

echo $cmd1
$cmd1
echo $cmd2
$cmd2
