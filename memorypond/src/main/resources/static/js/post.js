addEventListener("DOMContentLoaded", function(){
    // new ReviewWritePage();
    var menu = new Menu();
    var postContent = new PostContent();
    if(!document.querySelector('#comment'))return;
    var commentList = new CommentList();
    var commentForm = new CommentForm();
    commentForm.sendForm(commentList);
});

function Menu(){
    this.menuArea = document.querySelector('.menu-wrapper');
    this.visibleState = true;
    this.showMenu();
}

Menu.prototype = {
    showMenu:function(){
        $(window).scroll(  
            function(){   
                if($(window).scrollTop() > 50){    
                    $('.menu-title').fadeIn( 'linear' );
                    if(this.visibleState == true){
                        $('.menu').toggleClass('visible');
                        this.visibleState = false;
                    }
                }else{  
                    $('.menu-title').fadeOut( 'linear' );
                    // $('.menu').fadeOut( 'linear' );
                    if(this.visibleState == false){
                        $('.menu').toggleClass('visible');
                        this.visibleState = true;
                    }
                }  
            }.bind(this)  
        );
    }
}

function PostContent(){
    this.contentArea = document.querySelector('#content');
    this.displayContent();
    this.deleteContent();
}

PostContent.prototype = {
    displayContent: function(){
        var pathArray = window.location.pathname.split('/');
        var postId = pathArray[pathArray.length -1];
        var xhr = new XMLHttpRequest();
        xhr.onload = function(){
            var data = JSON.parse(xhr.responseText);
            if(xhr.status === 200){
                this.contentArea.querySelector('.title').textContent += data.questionName;
                this.contentArea.querySelector('.author').textContent += data.username;
                this.contentArea.querySelector('.content').textContent += data.content;               
            }else{
                console.error(xhr.responseText);
            }
        }.bind(this);
        var url = "/api/posts/" + postId;
        xhr.open("GET", url);
        xhr.send();
    },
    deleteContent:function(){
        var btnRemove = this.contentArea.querySelector('.removal');
        if(btnRemove == null) return;

        var pathArray = window.location.pathname.split('/');
        var postId = pathArray[pathArray.length -1];
        
        btnRemove.addEventListener('click', (evt)=>{
            evt.preventDefault();
            var xhr = new XMLHttpRequest();
            xhr.onload = function(){
                var data = JSON.parse(xhr.responseText);
                if(xhr.status === 200){
                    location.href='/main';
                }else{
                    alert("삭제에 실패하였습니다.");
                    console.error(xhr.responseText);
                }
            }.bind(this);
            var url = "/api/posts/" + postId;
            xhr.open("DELETE", url);
            xhr.send();
        })
    }
}

function CommentList(){
    this.commentListArea = document.querySelector('.comment-list');
    this.commentTemplate = document.querySelector('#comment_item').innerHTML;
    this.getComments();
}

CommentList.prototype = {
    getComments: function(){
        var pathArray = window.location.pathname.split('/');
        var postId = pathArray[pathArray.length -1];

        var xhr = new XMLHttpRequest();
        xhr.onload = function(){
            if(xhr.status === 200){
                this.commentListArea.innerHTML = "";
                var bindTemplate =  Handlebars.compile(this.commentTemplate);
                var data = JSON.parse(xhr.responseText);
                data.forEach((item)=>{
                    this.commentListArea.innerHTML += bindTemplate(item);
                });
            }else{
                console.error(xhr.responseText);
            }
        }.bind(this);
        var url =  "/api/posts/" + postId + "/comments";
        xhr.open("GET", url);
        xhr.send();
    }
}

function CommentForm(){
    this.commentFormArea = document.querySelector('#comment');
    this.intializeForm();
}

CommentForm.prototype = {
    sendForm:function(commentList){
        var pathArray = window.location.pathname.split('/');
        var postId = pathArray[pathArray.length -1];

        var btnSend = this.commentFormArea.querySelector('#send');
        btnSend.addEventListener('click',(evt)=>{
            evt.preventDefault();

            if(!this.validateForm())
                return false;

            var xhr = new XMLHttpRequest();
            let data = {
                "content" : this.commentFormArea.querySelector("#message").value,
                "postId" : postId
            };
            data = JSON.stringify(data);
            xhr.onload=function(){
                if(xhr.status === 201){
                    var lotusData = JSON.parse(xhr.responseText);
                    var socket = io("http://13.209.97.183:3000");
                    var stringData = JSON.stringify(lotusData)
                    socket.emit("send_fish_data", encodeURI(stringData));       
                    socket.on("success", (res)=>{
                        socket.disconnect();
                        console.log("data sending success to socket server");  
                    });     
                    commentList.getComments();

                    document.querySelector('#message').value = '';
                }else{
                    console.error(xhr.responseText);
                }
                
            }.bind(this);

            var url = "/api/comments";
            xhr.open("POST", url);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(data);
        });
    },
    validateForm: function(){
        var content = this.commentFormArea.querySelector("#message").value;
        if(content.lengt < 3){
            errorSection.textContent = "3글자 이상 메세지를 입력해 주세요.";
            return false;
        }
        return true;
    },
    intializeForm: function(){
        var resetBtn = this.commentFormArea.querySelector('#reset');
        resetBtn.addEventListener('click', (evt)=>{
            this.commentFormArea.querySelector("#message").value = '';
        });
    }
}