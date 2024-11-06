// 타입에 따라 다른 창 보여주기
$(document).ready(function() {
	$('button').click(function(){
		const buttonClass = $(this).attr('class');
		console.log(buttonClass);	

		$('.price_list').hide();
		
		if(buttonClass=="reward"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("적립금")) {
			    	$(this).show();
			    }
			});
		}
		else if(buttonClass=="point"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("포인트")) {
			    	$(this).show();
			    }
			});
		}
		else if(buttonClass=="plus"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("충전")) {
			    	$(this).show();
			    }
			});
		}
		else if(buttonClass=="minus"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("사용")) {
			    	$(this).show();
			    }
			});
		}
		else if(buttonClass=="cancel"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("취소")) {
			    	$(this).show();
			    }
			});
		}
		else if(buttonClass=="end"){
			$('.price_list').each(function() {
				const listId = $(this).attr('id');
			    if (listId.includes("소멸")) {
			    	$(this).show();
			    }
			});
		}
		else{
			const list = $('.price_list').show();
		}
		
	});
});

// 시간 정리해서 보여주기
$(document).ready(function(){
	
	$('.datetime').each(function(){
		let datetime = $(this).text();
		console.log(datetime)
	})
})