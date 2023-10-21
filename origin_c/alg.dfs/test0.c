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
[[1,0,1,1,0,1,0,1],
[1,0,1,1,0,1,1,1],
[0,0,0,0,0,0,0,1]]
Output: 6
最大的岛屿面积为6，位于最右侧。
 */

//int dfs(int** grid, int rows, int cols, int i, int j) {
int dfs(int grid[MAX_ROWS][MAX_COLS], int rows, int cols, int i, int j) {
    // 边界条件判断
    if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != 1) {
        return 0;
    }

    // 将当前格子设为0，避免重复访问
    grid[i][j] = 0;

    // 统计连续的陆地格子的数量
    int area = 1;

    // 访问当前格子的上下左右四个相邻格子
    area += dfs(grid, rows, cols, i - 1, j);  // 上
    area += dfs(grid, rows, cols, i + 1, j);  // 下
    area += dfs(grid, rows, cols, i, j - 1);  // 左
    area += dfs(grid, rows, cols, i, j + 1);  // 右

    return area;
}

//int maxAreaOfIsland(int** grid, int rows, int cols) {
int maxAreaOfIsland(int grid[MAX_ROWS][MAX_COLS], int rows, int cols) {
    // 边界条件判断
    if (grid == NULL || rows == 0 || cols == 0) {
        return 0;
    }

    int max_area = 0;

    int i,j;
    // 遍历矩阵中的每个格子
    for (i = 0; i < rows; i++) {
        for (j = 0; j < cols; j++) {
            // 如果当前格子是陆地，则进行DFS遍历并更新最大岛屿面积
            if (grid[i][j] == 1) {
                int area = dfs(grid, rows, cols, i, j);
                if (area > max_area) {
                    max_area = area;
                }
            }
        }
    }

    return max_area;
}

int main() {
#if 0
    int grid[MAX_ROWS][MAX_COLS] = {
        {1, 1, 0, 0, 0},
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


    int max_area = maxAreaOfIsland(grid, rows, cols);
    printf("Max Area of Island: %d\n", max_area);

    return 0;
}
