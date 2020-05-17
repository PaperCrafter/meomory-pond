addEventListener("DOMContentLoaded", function(){
    var scaleSection = new ScaleSection();
});

function ScaleSection(){
    this.weightArea = document.querySelector('.weight-area');
    this.getWeight();
}

ScaleSection.prototype = {
    getWeight:function(){
        var xhr = new XMLHttpRequest();
        params = this.getUrlParams();
        xhr.onload=function(){
            if(xhr.status === 200){
                var data = JSON.parse(xhr.responseText);
                this.weightArea.querySelector('.value').textContent = data.weight + 'g';
            }else{
                console.error(xhr.responseText);
            }
        }.bind(this);
        var url = "/api/posts/" + params.postId;
        xhr.open("GET", url);
        xhr.send();
    },
    getUrlParams: function() {
        var params = {};
        window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
        return params;
    }
}