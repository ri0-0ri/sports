$(document).ready(function() {
    
	// 배송지
	$('.deliveryplace').each(function(){
		let deliveryplace = $(this).text().trim();
		if(deliveryplace.includes("//")){
			deliveryplace = deliveryplace.replace("//"," ");
		$(this).text(deliveryplace);		
		}
	})
	
	// 결제수단
	$('.sudan').each(function() {
	      let sudan = $(this).text().trim();	      
	      // sudan의 값에 따라 텍스트를 직접 변경
	      if(sudan === "1") {
	          $(this).text("포인트 결제");
	      } else if(sudan === "2") {
	          $(this).text("간편 결제");
	      } else {
	          $(this).text("적립금 결제");
	      }
	  });
	  
	  // 상세보기
	  $('.open_btn').click(function() {
	      // .open_btn 클릭 시 바로 다음에 위치한 .infobox를 찾아서 display: block으로 설정
	      $(this).closest('.item-inforinfor').find('.infobox').css('display', 'block');
	  });

	  // 상세보기 닫기
	  $('.close_btn').click(function() {
	      $(this).closest('.infobox').css('display', 'none');
	  });
	  
});