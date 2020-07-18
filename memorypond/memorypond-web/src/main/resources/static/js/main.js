addEventListener("DOMContentLoaded", function(){
    // new ReviewWritePage();
    var menu = new Menu();
    var postList = new PostList();
    var pageList = new PageList();
    postList.getData(0, pageList);
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

function PostList(){
    this.postListArea = document.querySelector('.post-list');
    this.sizePerPage = 10;
    this.postItemTemplate = document.querySelector('#card_item').innerHTML;
}

PostList.prototype = {
    getData: function(page, pageList){
        var xhr = new XMLHttpRequest();
        xhr.onload=function(){
            if(xhr.status === 200){
                this.postListArea.innerHTML = "";
                var bindTemplate =  Handlebars.compile(this.postItemTemplate);
                var data = JSON.parse(xhr.responseText);
                data.content.forEach((item)=>{
                    this.postListArea.innerHTML += bindTemplate(item);
                }) 
                var items = this.postListArea.querySelectorAll('.post-item');
                items.forEach((item)=>{
                    item.addEventListener('click',(evt)=>{
                        const id = item.querySelector('div').getAttribute('id');
                        location.href= "/post/" + id;
                    })
                })          
                pageList.createPagenation(data, this);
            }else{
                console.log("error");
            }
        }.bind(this)
        var url = "/api/posts" + "?page=" + page + "&size=" + this.sizePerPage + "&sort=createAt,DESC";
        xhr.open("GET", url);
        xhr.send();
    },
}

function PageList(){
    this.pageArea = document.querySelector('#page .page-list');
    this.postItemTemplate ="<li class='page-index'><a href='#' class=''></a></li>"
}

PageList.prototype = {
    createPagenation:function(data, postList){
        var startPage = 1;
        var currentPage = data.pageable.pageNumber + 1;
        var endPage = data.totalPages;
        var pagenationSize = 1;

        var ellipsisStart = (startPage < currentPage - pagenationSize - 1);
        var ellipsisEnd = (endPage > currentPage + pagenationSize + 1);

        this.pageArea.innerHTML = "";

        for(var i = startPage; i <= endPage; i++){
            if(i == startPage ){
                this.pageArea.innerHTML += this.postItemTemplate;
                this.pageArea.lastElementChild.querySelector('a').textContent = startPage;
                if(ellipsisStart){
                    this.pageArea.innerHTML += this.postItemTemplate;
                    this.pageArea.lastElementChild.querySelector('a').textContent = "...";
                }
                continue
            }
            else if(i == endPage){
                if(ellipsisEnd){
                    this.pageArea.innerHTML += this.postItemTemplate;
                    this.pageArea.lastElementChild.querySelector('a').textContent = "...";
                }
                this.pageArea.innerHTML += this.postItemTemplate;
                this.pageArea.lastElementChild.querySelector('a').textContent = endPage;
                continue;
            }
            else if(i < currentPage - pagenationSize || i > currentPage + pagenationSize )
                continue;

            this.pageArea.innerHTML += this.postItemTemplate;
            this.pageArea.lastElementChild.querySelector('a').textContent = i;
        }

        var pageItems = this.pageArea.querySelectorAll('a');
        pageItems.forEach((item)=>{
            item.addEventListener('click', (evt)=>{
                evt.preventDefault();
                var pageIdx = item.innerHTML;
                if(pageIdx == "...") return;
        
                postList.getData(pageIdx-1, this);
            })
        })
    }
}