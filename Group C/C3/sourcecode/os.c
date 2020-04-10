/*
 ============================================================================
 Name        : Linux.c
 Author      : Vaibhav
 Version     : 1.4
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include<unistd.h>
int main(void) {
	int choice;
	char *args[]={"ps",NULL};
	pid_t pid1,pid2;
	char *command[]={"ls",NULL};
	int pid;
	char *argv[]={"NULL"};
	int status;
	char *join[]={"join","file1","file2",NULL};
	puts("UNIX COMMANDS EXECUTION");
	do
	{
		printf("Menu\n1.PS\n2.JOIN\n3.FORK\n4.WAIT\n5.Exec\n0.Exit\nENter Your choice:");
		scanf("%d",&choice);
		switch(choice)
		{
		case 1:

			execvp(args[0],args);
			break;
		case 2:
			execvp(join[0],join);
			break;
		case 3:
					pid=fork();	//system call
	if(pid<0)
	{
		printf("\nUnsuccessful execution\n");
		exit(1);	//system call
	}
	else if(pid>0)
	{
		printf("\nI am parent(started) and spawned new child with PID=%d\n",pid);
		pid1=wait(&status);	//system call
		printf("\nParent terminating.. & saying: child with PID=%d is terminated\n",pid1);
	}
	else if(pid==0)
	{
		printf("\nI am child(started) with PID=%d\n",pid);
		sleep(3);
		execv("/usr/bin/firefox",argv);	//system call
		printf("\nChild terminating..\n");
	}
		break;
		case 4:

					pid=fork();
					int varlcl=0;
					int vargbl;
					if(pid==0)
					{
						vargbl++;
						varlcl++;
						printf("Child process %d",getpid());
						printf("\n");
						//4wait(&status);
						printf("Child process :: var_lcl [%d] , var_gbl[%d] ",varlcl,vargbl,"\n");
						printf("\n");

					}
					else if(pid>0)
					{
						printf("Parent process %d",getpid());
						printf("\n");
						varlcl=10;
						vargbl=20;

						printf("Parent process :: var_lcl [%d] , var_gbl[%d] ",varlcl,vargbl,"\n");

						printf("Child process exit code %d ",WIFEXITED(status));
						wait(&status);


					}
					break;
		case 5:

			//printf("Enter COmmand you want to execute:");
			//scanf("%s", command[0]);
			execvp(command[0],command);
			break;
		}
	}while(choice!=0);
	return EXIT_SUCCESS;
}
