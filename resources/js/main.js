addEventListener("DOMContentLoaded", function(){
    // new ReviewWritePage();
    var menu = new Menu();
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