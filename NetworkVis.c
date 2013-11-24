#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argz,char* args[])
{
	int size;
	printf ("Enter the number of nodes.\n");
	scanf("%d",&size); 
	int** network;
	network = (int**)malloc(size*sizeof(int));
	int i = 0;
	for(i = 0; i < size; i++)
		network[i] = (int*)malloc(size*sizeof(int));
	typedef struct Node * nodes;
	nodes =(Node*)malloc(size*sizeof(Node));
	return 0;
}

typedef struct {
	double x;
	double y;
} Node;

