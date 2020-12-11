Allison Li, Davin Kyi, Duaa Zaheer
CSE143 D Final Project
Parsely User Guide

	To develop Parsely, we used the Eclipse Integrated Development Environment (IDE) to write, store, and compile our code. Using Github, we were able
to work collaboratively. Our code repository can be found at https://github.com/duaaz/Parsely. You will need to clone this repository with all our 
files onto your computer. To properly display some of the graphical interfaces (bell curves) included in our project, you will also need to  download
the StdDraw pack from Princeton University at this link https://introcs.cs.princeton.edu/java/stdlib/. Using the Command Prompt, you will need to 
navigate to the directory (folder) that you cloned the Parsely repository into. You can do this by using the cd (change directory) command to switch 
directories (paths) until you reach the correct folder. In the Parsely folder is a jar titled ExecutableJar.jar, which you can use to run our program.
The next thing that will be typed into the command prompt is ‘java -jar ExecutableJar.jar’. Our program will immediately start running. 
	For reference, our main method that is being ran is Client.java in the src folder. When this is ran, you will be prompted to enter a list of required 
skills (keywords you’re strictly looking for) for the job, separated by commas. Next, you will be prompted to enter in a list of desired skills separated
by commas, which would add points to overall resumes and be used to set candidates apart from others. Keep in mind that these ‘skills’ can also just be 
keywords you’re looking to spot out in a resume. For example if you’re hiring for a specific company and you’re giving special consideration to candidates 
who have had prior experience at this company, you can also list the company name as a ‘desired skill’ so extra weight is given to those candidates. 
Next, you will be asked if you value experience length, skills, or both equally when comparing candidates to each other. You can either type in 
‘experience’, ‘skills’ or ‘’equal’. If you choose skills, this will give more weight in terms of “points” for each desired skill that the candidate 
has, and vice versa for length of experience (more weight in terms of how many months they’ve had experience with associated with the skills). If you 
choose equal, both these factors will be weighed equally quantitatively when adding up candidate “points”. 
	Next, you will be given the option to either manually type in the names of each Resume file for each candidate you are looking at separated by 
commas (Ex: “BobBuilderResume.txt, SteveJobsResume.txt, MeganTheeStallion.txt”) OR type in the name of one file that contains all the resume file names
listed out one after the other (no commas for separation needed). Before getting to the file names, you can either type in ‘type’ or ‘read’ to specify
which method you wish to use. For testing purposes and to make it so that you don’t have to type in the name of each resume manually, the Parsely project 
includes a variety of example resume test files we’ve accounted for in our code, and a holistic file titled “AllResumeNames.txt” which you can use and 
type in for the ‘read’ option. 
	Finally, you will be prompted to either print out the evaluation results for Parsely either ‘individually’ or as a ‘group’. The ‘group’ option prints 
out overall statistics for the entire candidate pool, with a basic overview of each candidate included (their name, whether they were rejected/accepted, 
and a list of the required or desired skills you first entered that their resume included). Additionally, this option prints out bell curve for overall
group statistics in terms of “points” scored. The ‘individually’ option prints out each candidate in more detail (their name, whether they were 
accepted/rejected, reason for their acceptance/rejection, as well as the basic or desired skills included in their resume). These candidate results are
each represented one by one every 5 seconds, accompanied by a new tab that opens up with a bell curve for the group statistics with an ‘x’ and the candidate
name that marks where that candidate lies on the curve in comparison to their entire group). Every 5 seconds, the bell curve tab for the prior candidate 
will be replaced by a new tab representing the next candidate. 

Link to video walkthrough (with Eclipse IDE): https://drive.google.com/file/d/17VEgmkzEDiO3s0rqGBwC_7mvAUUV0gXm/view?usp=sharing