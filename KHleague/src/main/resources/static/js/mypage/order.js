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
	  
	  // 주문취소 상태띄우기
	  $('.state').each(function(){
		const state = $(this).text().trim();
		if(state == "배송중" || state=="배송완료"){
			$(this).closest('.item-inforinfor').find('.refundbox').css('display', 'none');
		}
	  })
	  
	  // 주문상태에 따라 바꿔주기
	  $('.state').each(function(){
	  const state = $(this).text().trim();
	  
		let received = parseInt($('#received').text().trim());
		if(state == "주문접수"){
		  	received += 1;
			$('#received').text(received);
		 }
		  
		let productReady = parseInt($('#product_ready').text().trim());
		if (state == "상품준비중") {
			productReady += 1;
			$('#product_ready').text(productReady);
		}
	
		let deliveryReady = parseInt($('#delivery_ready').text().trim());
		if (state == "배송준비중") {
			deliveryReady += 1;
			$('#delivery_ready').text(deliveryReady);
		}
	
		let deliveryIng = parseInt($('#delivery_ing').text().trim());
		if (state == "배송중") {
			deliveryIng += 1;
			$('#delivery_ing').text(deliveryIng);
		}
	
		let deliveryOk = parseInt($('#delivery_ok').text().trim());
		if (state == "배송완료") {
			deliveryOk += 1;
			$('#delivery_ok').text(deliveryOk);
		}
		})
	
	$('#btn .all').addClass('act');	
	// 버튼 상태에 따라서 날짜별로 보여주기	
	$('#btn button').click(function() {
		$('#btn button').removeClass('act');
	    $(this).addClass('act');
		
		const selectedPeriod = $(this).text().trim();   // 클릭된 버튼의 텍스트 가져오기
		filterOrdersByDate(selectedPeriod); 
	});
	
	function filterOrdersByDate(period) {
	        const currentDate = new Date();
	        let startDate, endDate;

	        switch (period) {
	            case '1개월':
	                startDate = new Date();
	                startDate.setMonth(currentDate.getMonth() - 1);
	                break;
	            case '3개월':
	                startDate = new Date();
	                startDate.setMonth(currentDate.getMonth() - 3);
	                break;
	            case '6개월':
	                startDate = new Date();
	                startDate.setMonth(currentDate.getMonth() - 6);
	                break;
	            case '12개월':
	                startDate = new Date();
	                startDate.setMonth(currentDate.getMonth() - 12);
	                break;
	            case '전체':
	                startDate = null;
	                endDate = null;
	                break;
	            default:
	                startDate = null;
	                endDate = null;
	                break;
	        }

	        // 각 주문을 확인하여 해당 기간 내에 포함되는지 여부 확인
	        $('.item-inforinfor').each(function() {
	            const orderDateText = $(this).find('#date').text().trim();
	            const orderDate = new Date(orderDateText);  // 주문 날짜를 Date 객체로 변환

	            if (startDate && orderDate >= startDate) {
	                // 조건에 맞으면 해당 항목 보이기
	                $(this).show();
	            } else if (!startDate) {
	                // 전체 기간일 경우 모두 보이기
	                $(this).show();
	            } else {
	                // 조건에 맞지 않으면 해당 항목 숨기기
	                $(this).hide();
	            }
	        });
	    }
	
});

function go_refund(ordernum){
	console.log(ordernum);
	window.location.href = "/refund/refund?ordernum=" + ordernum;
}	