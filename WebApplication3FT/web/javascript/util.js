/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function zwrocIdZezwolenia()
{
//dlbWybierzZezwolenia=open('/eSprawozdania/obsluga?command=lzezw','secondWindow','scrollbars=yes,menubar=yes,toolbar=yes,status=yes')");
}

//--------------------------------------------
function pokazRaportWDrugimOknie()
{
//raport=open('/eSprawozdania/obsluga?command=raport','secondWindow','menubar=no,scrollbars=yes,maximize=yes,resizable=yes');");
}
function sumujPola(ElementName)
{
   document.getElementById(ElementName).style.color ='#FF0000';
   document.getElementById('SumaGrup').value = '' +
   parseFloat(document.getElementById('Grupa1').value)+parseFloat(document.getElementById('Grupa2').value)+ 
   parseFloat(document.getElementById('Grupa3').value)+parseFloat(document.getElementById('Grupa4').value)+ 
   parseFloat(document.getElementById('Grupa5').value)+parseFloat(document.getElementById('Grupa6').value)+ 
   parseFloat(document.getElementById('Grupa7').value)+parseFloat(document.getElementById('Grupa8').value)+ 
   parseFloat(document.getElementById('Grupa9').value)+parseFloat(document.getElementById('Grupa0').value)+ 
   parseFloat(document.getElementById('SrTrwWBudowie').value)+parseFloat(document.getElementById('WNiP').value);
}

function sprawdzDate(pole)
{
if (pole.value.length>0)
{
if (pole.value.search(/\\d{4}(-\\d{2}(-\\d{2}))/) != -1)
{
}
else 
{alert('Zly format daty')}
}
}


function sprawdzMiesiac(pole)
{
if (pole.value!=1 && pole.value!=2 && pole.value!=3 && pole.value!=4 && pole.value!=5 && pole.value!=6 && pole.value!=7 && pole.value!=8 && pole.value!=9 && pole.value!=10 && pole.value!=11 && pole.value!=12 )
{
alert('Miesi¹c poza zakresem')
}
else {}
}

function showTip(current,e,text)
{
 if (document.all&&document.readyState=='complete')
 {
  document.all.obszarOkna.innerHTML='<p style=\"border:1px solid black\">'+text+'</p>';
  document.all.obszarOkna.style.pixelLeft=event.clientX+document.body.scrollLeft+10;
  document.all.obszarOkna.style.pixelTop=event.clientY+document.body.scrollTop+10;
  document.all.obszarOkna.style.visibility='visible';
}
}

function hideTip()
{
if (document.all)
    document.all.obszarOkna.style.visibility='hidden';
    else if (document.layers)
    {
    clearInterval(currentscroll);
    document.obszarOkna.visibility='hidden';
    }
}

function sprawdzPola(current,e,text)
{
 if (document.getElementById('IdZezwolenia').value=='null' || document.getElementById('RokSpr').value=='' || document.getElementById('RokSpr').value=='')
 {
 alert(text);
 }
 else 
 {
 sprawdzMiesiac(document.getElementById('MiesiacSpr'));
 }
}
