addEventListener("DOMContentLoaded", function(){
    var menu = new LoginForm();
});

function LoginForm(){
    this.form = document.querySelector('#main');
    this.activateBtnSend();
    this.activateBtnMain();
}

LoginForm.prototype = {
    activateBtnSend:function(){

    },
    activateBtnMain:function(){
        let btnMain = this.form.querySelector('.to-main');
        btnMain.addEventListener('click', (evt)=>{
            location.href="/main";
        })
    },
    validateForm:function(){

    }
}