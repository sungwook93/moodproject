//관리자 페이지 메뉴
$(document).ready(function() {
	
	//처음 페이지들어갈시 상품관리메뉴만보이게
	$("#admintitle2").css("display", "none");
	$("#admintitle3").css("display", "none");
	$("#adminarea").css("text-align", "center");
	
	//메뉴 클릭시 보이는 페이지내용
	$("#titlesection1").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline");
	});	
	$("#titlesection2").click(function() {
		$("#admintitle").css("display", "inline");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
	});	
	$("#titlesection3").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline");
		$("#admintitle3").css("display", "none");
	});
	$("#titlesection4").click(function() {
		$("#admintitle").css("display", "inline");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
	});	
	$("#titlesection5").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline");
		$("#admintitle3").css("display", "none");
	});	
	$("#titlesection6").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline");
	});	
	$("#titlesection7").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline");
		$("#admintitle3").css("display", "none");
	});	
	$("#titlesection8").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline");
	});	
	$("#titlesection9").click(function() {
		$("#admintitle").css("display", "inline");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
	});		
});