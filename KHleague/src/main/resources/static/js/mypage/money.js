// 타입에 따라 다른 창 보여주기
$(document).ready(function() {
	$('#all').addClass('act');
	$('button').click(function(){
		const buttonId = $(this).attr('id');
		console.log(buttonId);
		
		$('button').removeClass('act');	
		$('.price_list').hide();
		
		if(buttonId=="reward"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("적립금")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else if(buttonId=="points"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("포인트")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else if(buttonId=="plus"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("충전")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else if(buttonId=="minus"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("사용")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else if(buttonId=="cancel"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("취소")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else if(buttonId=="end"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("소멸")) {
			    	$(this).show();
			    }
			});
			$(this).addClass('act');	
		}
		else{
			const list = $('.price_list').show();
			$(this).addClass('act');	
		}
		
	});
});

// 시간 정리해서 보여주기
$(document).ready(function(){
	
	$('.datetime').each(function(){
		let datetime = $(this).text().replace("T"," ");
		console.log(datetime)
		$('.datetime').text(datetime);
	})
})