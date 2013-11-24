#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
	int size;
	scanf("%d",&size);
	int * a;
	a = malloc(size*sizeof(int));
	a[0] = 5;
	a[1] = 12;
	printf ("%d \n",sizeof(int));
	printf ("value: %d & %d\n",a[0], a[1]);
	printf ("size of array: %d\n",sizeof(a));
	return 0;
}
