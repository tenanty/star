/**
 * 
 */
var MainPage = {};
MainPage.viewModel = function() {
	var self = this;
	this.menus = ko.observableArray();
	
	this.loadMenuData = function() {
		$.ajax({
            cache: false,
            dataType:"json",
            type: "POST",
            url:APPpath+"/edition/loadMenus",
            data:$('#artifactQueryForm').serialize(),// 你的formid
            async: false,
            success: function(data) {
            	 self.menus.removeAll();
	             for(var i in data){
	            	 self.menus.push({menu:data[i]});
	             }
            }
        });
	};

}

$(function() {
	MainPage.vm = new MainPage.viewModel();
	ko.applyBindings(MainPage.vm,$("#main-menu")[0]);
	MainPage.vm.loadMenuData();
    $("#main-menu ul:eq(0) li:eq(0) > a").click();
});

function loadPageContent(name){
	var content = $('#load-page-content-data');
	var url = APPpath+"/edition/";
	url = url + name;
	$.ajax({
        type: "GET",
        cache: false,
        url: url,
        dataType: "html",
        success: function(res) 
        {
            $('.sub-menu > li.active').removeClass('active');
            $('.sub-menu > li.' + name).addClass('active');
            content.html(res);
        },
        async: false
    });
	
}

function logout(){
	location.href = APPpath+"/logout";
}