#include <stdlib.h>
#include <math.h>

int minTimeToVisitAllPoints(int** points, int pointsSize, int* pointsColSize) {
    int totalTime=0;
    for(int i=0;i<pointsSize-1;i++){
        int dx=abs(points[i+1][0]-points[i][0]);
        int dy=abs(points[i+1][1]-points[i][1]);
        totalTime +=(dx>dy) ? dx:dy;
    }
    return totalTime;

    
}