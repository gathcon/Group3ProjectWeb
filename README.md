#GIT Best Practices
1. Create a branch for each issue/task you are taking on
2. Work on that branch fo that issue only
3. Merge back into master **if and only if** all tests pass

#GIT Commands

##Get your current status
	$ git status

##Show all branches. (* denotes the branch that is currently checked out.)
	$ git branch

##Create a branch
	$ git branch <name of branch>

##While still on branch 'master', push the new branch to the remote server
	$ git push origin <name of branch>

##Checkout the new branch
	$ git checkout <name of branch>

##Add files to the staging area
	$ git add <filename>

##Commit changes with a descriptive message
	$ git commit -m "<message>"

##Push changes to remote branch
	$ git push origin <name of branch>

##Pull changes from remote branch
	$ git pull origin <name of branch>
	
##Merge branch to master
	$ git checkout master (first checkout master branch)
	$ git merge <name of branch>
	
##Delete branch after merge
	$ git branch -d <name of branch>
