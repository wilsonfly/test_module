#include <stdio.h>
#include <stdlib.h>

#define MAX_ROWS 100
#define MAX_COLS 100

/*
题目描述
给定一个二维的0-1 矩阵，其中0 表示海洋，1 表示陆地。单独的或相邻的陆地可以形成岛
屿，每个格子只与其上下左右四个格子相邻。求最大的岛屿面积。
输入输出样例
输入是一个二维数组，输出是一个整数，表示最大的岛屿面积。
Input:
[1,0,1,1,0,1,0,1],
[1,0,1,1,0,1,1,1],
[0,0,0,0,0,0,0,1]
Output: 6
最大的岛屿面积为6，位于最右侧。
 */

//202307
int dfs(int grid[MAX_ROWS][MAX_COLS],int rows,int cols,int i, int j)
{
    //if(i<0 || i>=rows || j<0 || j>=cols)
    if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]!=1)//重要边界条件grid[i][j]
        return 0;

    int area = 1;//能走进此接口就已经有1个面积了
    
    grid[i][j] = 0;//避免重复访问

    area += dfs(grid,rows,cols,i-1,j);
    area += dfs(grid,rows,cols,i+1,j);
    area += dfs(grid,rows,cols,i,j-1);
    area += dfs(grid,rows,cols,i,j+1);

    //grid[i][j] = 1;//恢复后深度优先搜索的结果及岛屿个数统计就不准了
    return area;
}

int maxAreaOfIsland(int grid[MAX_ROWS][MAX_COLS],int rows,int cols)
{
    if(grid==NULL || rows<=0 || cols<=0)
        return 0;

    int i,j;
    int area = 0;
    int max = 0;
    int count = 0;

    for(i=0; i<rows; i++)
    {
        for(j=0; j<cols; j++)
        {
            if(grid[i][j] == 1)
            {
                area = dfs(grid, rows, cols, i, j);
                max = max<area? area:max;
                count ++;//扩充题目，统计多少个岛屿;上面dfs一遍后相连的都已被置为0，所以不会重复统计
            }
        }
    }
    printf("island number:%d\n",count);
    return max;
}

void show(int data[MAX_ROWS][MAX_COLS], int rows, int cols)
{
    int i,j;
    for(i=0; i<rows; i++)
    {
        for(j=0; j<cols; j++)
        {
            printf("%d,",data[i][j]);
        }
        printf("\n");
    }

}

/*
1,1,0,1,1,
1,1,0,0,0,
0,1,1,1,1,
0,0,0,1,1,
island number:2
Max Area of Island: 10
0,0,0,0,0,
0,0,0,0,0,
0,0,0,0,0,
0,0,0,0,0,

dfs中添加恢复的话数据能恢复，但统计结果不准了
//grid[i][j] = 1;//恢复后深度优先搜索的结果及岛屿个数统计就不准了
1,1,0,1,1,
1,1,0,0,0,
0,1,1,1,1,
0,0,0,1,1,
island number:12
Max Area of Island: 25
1,1,0,1,1,
1,1,0,0,0,
0,1,1,1,1,
0,0,0,1,1,

 */
int main() {
#if 1
    int grid[MAX_ROWS][MAX_COLS] = {
        {1, 1, 0, 1, 1},
        {1, 1, 0, 0, 0},
        {0, 1, 1, 1, 1},
        {0, 0, 0, 1, 1}
    };
    int rows = 4;
    int cols = 5;
#else
    //int grid[3][8] = 
    int grid[MAX_ROWS][MAX_COLS] = 
    {
        {1,0,1,1,0,1,0,1},
        {1,0,1,1,0,1,1,1},
        {0,0,0,0,0,0,0,1}
    };
    int rows = 3;
    int cols = 8;
#endif


    show(grid, rows, cols);
    int maxArea = maxAreaOfIsland(grid, rows, cols);
    printf("Max Area of Island: %d\n", maxArea);
    show(grid, rows, cols);

    return 0;
}
