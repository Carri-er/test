$(document).ready(function(){
	/* 두번째 섹션 여행콕콕 */
	let homenavlist = $(".home_dl_nav_ul li");
	let dlment = $(".dl_ment span");
	let homedlimgAIcokcok = $(".home_dl_img_AIcokcok");
	let home_dl_img_hotPlace =$(".home_dl_img_hotPlace");
	let home_dl_img_AIPlaner =$(".home_dl_img_AIPlaner");
	let dl_refresh_button =$(".dl_refresh_button span");
	
		dlment.eq(0).hide();
		dlment.eq(1).hide();
		homedlimgAIcokcok.hide();
		home_dl_img_hotPlace.hide();
		dl_refresh_button.eq(0).hide();
		dl_refresh_button.eq(1).hide();
		
	homenavlist.eq(0).click(function(){
		homedlimgAIcokcok.show();
		dlment.eq(0).show();
		dlment.eq(1).hide();
		dlment.eq(2).hide();
		home_dl_img_hotPlace.hide();
		home_dl_img_AIPlaner.hide();
		dl_refresh_button.eq(0).show();
		dl_refresh_button.eq(1).hide();
		dl_refresh_button.eq(2).hide();
	});
	homenavlist.eq(1).click(function(){
		dlment.eq(1).show();
		dlment.eq(0).hide();
		dlment.eq(2).hide();
		homedlimgAIcokcok.hide();
		home_dl_img_hotPlace.show();
		home_dl_img_AIPlaner.hide();
		dl_refresh_button.eq(1).show();
		dl_refresh_button.eq(0).hide();
		dl_refresh_button.eq(2).hide();
	});
	homenavlist.eq(2).click(function(){
		dlment.eq(2).show();
		dlment.eq(0).hide();
		dlment.eq(1).hide();
		homedlimgAIcokcok.hide();
		home_dl_img_hotPlace.hide();
		home_dl_img_AIPlaner.show();
		dl_refresh_button.eq(2).show();
		dl_refresh_button.eq(0).hide();
		dl_refresh_button.eq(1).hide();
		
	});
})