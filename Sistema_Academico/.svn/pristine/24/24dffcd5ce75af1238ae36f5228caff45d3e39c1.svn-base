function detectar_tecla(){ 
    with (event){
        if (keyCode==115 && ctrlKey){
            event.keyCode = 0;
            event.cancelBubble = true;
            alert('Presionaste CTRL + F4');
            return false;
        }
        else if (keyCode==27) {
            Richfaces.hideModalPanel('mpx2');
            Richfaces.hideModalPanel('mp3');
            Richfaces.hideModalPanel('mp');
            return false;
        }
        }
} 
document.onkeydown = detectar_tecla; 

function getRightTop(ref) {
    var position = new Object();
    position.top = 0; //ref.offsetTop;
    position.left =0; // ref.offsetLeft+ref.clientWidth+6;
    return position;
}
function abrir(method,id,fecha_ini,fecha_fin){
    window.open("http://172.16.5.242:8080/printer/print.do?method="+method+"&id="+id+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin,"_blank");
}
 
function prompter() {
    var nro = prompt("Ingrese Comprobante", "");
    document.getElementById('form1:nro_oculto').value=nro;
}