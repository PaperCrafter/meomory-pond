addEventListener("DOMContentLoaded", function(){
    var menu = new RegisterForm();
});

function RegisterForm(){
    this.form = document.querySelector('#main');
    this.activateBtnSend();
}

RegisterForm.prototype = {
    activateBtnSend:function(){
        let btnSend = this.form.querySelector('#btnSend');
        let errorSercion = document.querySelector('.error-message');

        btnSend.addEventListener('click',(evt)=>{
            evt.preventDefault();
            const username = this.form.querySelector('#username').value;
            const password = this.form.querySelector('#password').value;
            const passwordConfirm = this.form.querySelector('#passwordConfirm').value;

            if(!this.validateForm(username, password, passwordConfirm)){
                alert("회원가입에 실패하셨습니다.")
                return false;
            }

            const data = {
                "username": username,
                "password": password
            }
            var xhr = new XMLHttpRequest();
            xhr.onload=function(){
                if(xhr.status === 201){
                    location.href="/login";
                }else{
                    var res = JSON.parse(xhr.responseText);
                    if(res.msg == "duplicated"){
                        errorSercion.textContent = "유저이름이 중복됩니다.";
                        return false;
                    }
                }
            }
            var url = "/api/members";
            xhr.open("POST", url);
            xhr.setRequestHeader('Content-Type', 'application/json'); 
            xhr.send(JSON.stringify(data));
        });
    },
    validateForm:function(username, password, passwordConfirm){
        let errorSercion = document.querySelector('.error-message');
        const passwordRegx = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{5,15}$/;
        if(username.length < 1 || username.length > 15){
            errorSercion.textContent = "유저이름 형식이 잘못되었습니다.";
            return false;
        }
        if(!passwordRegx.test(password)){
            errorSercion.textContent = "패스워드 형식이 잘못되었습니다.";
            return false;
        }
        if(password !== passwordConfirm){
            errorSercion.textContent = "비밀번호가 일치하지 않습니다."
            return false;
        }
        return true;
    }
}