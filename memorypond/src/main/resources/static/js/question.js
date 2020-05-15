addEventListener("DOMContentLoaded", function(){
    var form = new PostForm();
});

function PostForm(){
    this.form = document.querySelector('form');
    this.radioItemTemplate = document.querySelector('#radio_item').innerHTML;
    this.getCategories();
    this.changeQuestion();
    this.sendForm();
}

PostForm.prototype = {
    getCategories:function(){
        var xhr = new XMLHttpRequest();
        xhr.onload=function(){
            if(xhr.status === 200){
                var data = JSON.parse(xhr.responseText);
                var categoryArea = this.form.querySelector('#category');
                categoryArea.innerHTML = "";

                var option = document.createElement('option');
                option.value = 0;
                option.textContent = "질문 카테고리를 선택해 주세요";
                categoryArea.appendChild(option);

                data.forEach((item)=>{
                    var option = document.createElement('option');
                    option.value = item.id;
                    option.textContent = item.categoryName;
                    categoryArea.appendChild(option);
                });
            }else{
                console.error(xhr.responseText);
            }
        }.bind(this);
        var url = "/api/categories";
        xhr.open("GET", url);
        xhr.send();
    },
    changeQuestion:function(){
        var categoryArea = this.form.querySelector('#category');
        categoryArea.addEventListener('change', (evt)=>{
            var selectVal = $('#category option:selected').val();
            var xhr = new XMLHttpRequest();
            xhr.onload=function(){
                if( selectVal < 1){
                    radioArea.innerHTML = "";
                }

                if(xhr.status === 200){
                    var bindTemplate = Handlebars.compile(this.radioItemTemplate);
                    var data = JSON.parse(xhr.responseText);
                    var radioArea = this.form.querySelector('#radio');
                    radioArea.innerHTML = "";
                    data.forEach((item)=>{
                        radioArea.innerHTML += bindTemplate(item);
                    });
                }else{
                    console.error(xhr.responseText);
                }
            }.bind(this);
            var url = "/api/categories/" + selectVal + "/questions";
            xhr.open("GET", url);
            xhr.send();
        });
    },
    sendForm: function(){
        var btnSend = this.form.querySelector('#send');
        btnSend.addEventListener('click',(evt)=>{
            evt.preventDefault();

            if(!this.validateForm())
                return false;

            var xhr = new XMLHttpRequest();
            var radioVal = $('input[name="radioText"]:checked').val();
            let data = {
                "comment" : this.form.querySelector("#content").value,
                "username" : this.form.querySelector("#username").textContent,
                "questionId" : radioVal
            }

            data = JSON.stringify(data);
            xhr.onload=function(){
                if(xhr.status === 201){
                    var lotusData = JSON.parse(xhr.responseText);
                    var socket = io("http://13.209.97.183:3000");
                    var stringData = JSON.stringify(lotusData)
                    socket.emit("send_lotus_data", encodeURI(stringData));       
                    socket.on("success", (res)=>{
                        socket.disconnect();
                        console.log("data sending success to socket server");
                        location.href="/post-created";      
                    });     
                    //for exceiption control  
                    setTimeout(()=>{
                        socket.disconnect();
                        console.log("creation timeout");
                        location.href="/post-created";  
                    }, 3000);      
                }else{
                    console.error(xhr.responseText);
                }
            }.bind(this);

            var url = "/api/posts";
            xhr.open("POST", url);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(data);
        });
    },
    isEmpty: function(item){
        if(typeof item == "undefined" || item == null || item == "")
            return true;
        else
            return false ;
    },
    validateForm: function(){
        var errorSection = this.form.querySelector("#error-message");
        var categoryVal = $('#category option:selected').val();
        if(categoryVal < 1){
            errorSection.textContent = "카테고리를 선택해 주세요";
            return false;
        }
        var radioVal = $('input[name="radioText"]:checked').val();
        if(radioVal < 1 || this.isEmpty(radioVal)){
            errorSection.textContent = "올바른 주제를 선택해 주세요.";
            return false;
        }

        var content = this.form.querySelector("#content").value;
        if(content.lengt < 3){
            errorSection.textContent = "3글자 이상 메세지를 입력해 주세요.";
            return false;
        }
        return true;
    }
}