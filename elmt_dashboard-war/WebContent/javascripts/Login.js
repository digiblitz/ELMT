#-------------------------------------------------------------------------------
# * * Copyright: 2019 digiBlitz Foundation
#  * * 
#  * * License: digiBlitz Public License 1.0 (DPL) 
#  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
#  * * 
#  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
#  * * 
#  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
#  * * 
#  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//-------------------------function for Special Character
function isnotSpecial(str){
stringSpecialCheck="!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'"+"\"";
f4=1;
for(j=0;j<str.length;j++)
{if(stringSpecialCheck.indexOf(str.charAt(j))!=-1)
{f4=0;}}
if(f4==0)
{return true;}else{return false;}
}
//---------------------------------------------------------------------------------------------------
function myvalidate(){
//---------------------------------------------------------------------------------

//------------for User Email Login

if(document.form1.textfield.value=="")
{alert("Enter Email id for User Name");
 document.form1.textfield.focus();
 return false;}
if((document.form1.textfield.value==""))
{
alert("Enter the E-Mail ID in Owner Details");
document.form1.textfield.focus();
return false;
}

if(!(document.form1.textfield.value==""))
{strmail2=document.form1.textfield.value;
firstat2=strmail2.indexOf("@");
lastat2=strmail2.lastIndexOf("@");
strmain2=strmail2.substring(0,firstat2);
strsub2=strmail2.substring(firstat2,document.form1.textfield.value.length);
if(strmail2.length>120)
{alert("Email in User Name is out of range");
 document.form1.textfield.focus();
return false;}
if(strmain2.indexOf('  ')!=-1 || firstat2==0 || strsub2.indexOf('  ')!=-1 )
{alert("Enter a valid Email in User Name");
 document.form1.textfield.focus();
return false;}
if(isnotSpecial(strmain2) || isnotSpecial(strsub2))
{alert("Enter Email in User Name");
 document.form1.textfield.focus();
return false;}

k2=0;
strlen2=strsub2.length;
for(i=0;i<strlen2-1;i++)
{ if(strsub2.charAt(i)=='.')
{k2=k2+1;}}
if(k2>2)
{alert("Enter a valid Email in User Name");
 document.form1.textfield.focus();
return false;}
if(firstat2==-1|| lastat2==-1)
{alert("Enter a valid Email in User Name");
document.form1.textfield.focus();
return false;}
if(Number(strmain2))
{alert("Enter a valid Email in User Name");
 document.form1.textfield.focus();
 return false;}
if(firstat2 != lastat2 )
{alert("Enter a valid Email in User Name");
 document.form1.textfield.focus();
return false;}
firstdot2=strmail2.indexOf(".",firstat2);
lastdot2=strmail2.lastIndexOf(".");
if(firstdot2 == -1 || lastdot2 == -1 || ((strmail2.length-lastdot2)==1))
{alert("Enter a valid Email in User Name");
 document.form1.textfield.focus();
 return false;}
}

if(document.form1.textfield2.value=="")
{alert("Enter Password");
 document.form1.textfield2.focus();
 return false;}


return true;
}


