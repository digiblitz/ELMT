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
//--------------------------------------------------------------------------------
//------------------------------
function Dispall()
{
i=0;
k=0;	
sum=0;
arrayval = new Array();
n=9;
chosen=false;
for(i=3;i<=n;i=i+2)
{
if(document.frmAdvAppRefund.elements[i].status==true)
 {chosen=true;}}
if(!chosen)
{alert("Check any of the Refund Type");
 return false; }

for(i=3,j=4;i<=n;i=i+2,j=j+2)
{ if(document.frmAdvAppRefund.elements[i].status)
  {k=k+1;
  if(document.frmAdvAppRefund.elements[j].value=="")
  { alert("Enter Values in the corresponding checked Values");
    return false;}
  arrayval[k]=Number(document.frmAdvAppRefund.elements[j].value);
   }}

for(ii=1;ii<=k;ii++)
{  sum = arrayval[ii]+sum;  }

/* if(sum>document.frmAdvAppRefund.approveamt.value)
 {alert("The Total Amount should be less than Approved Amount");
  return false;}*/
//alert(document.frmAdvAppRefund.approveamt.value);

if(sum>document.frmAdvAppRefund.balamt.value)
 {alert("The Total Amount should be less than Balance Amount");
  return false;}

return true;
}
