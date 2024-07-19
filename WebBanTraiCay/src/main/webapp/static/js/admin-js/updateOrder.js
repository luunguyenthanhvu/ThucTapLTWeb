'use strict';

var cloudName = 'dter3mlpl';
var apiKey = '899244476586798';
var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

$('.table-wrapper .table-sanpham .img-product img').each((_, elements) => {
  const publicId = $(elements).data('assets');
  const imgUrl = cl.url(publicId);
  const imgDefault = `${window.context}/static/images/default-fruit.jpg`;
  // check if image is exits
  if (imgUrl !== null) {
    $(elements).prop('src', imgUrl);
  } else {
    $(elements).prop('src', imgDefault);
  }
})

function getShipmentDetails(productId) {
  // Hiển thị SweetAlert2 modal trước khi thực hiện AJAX request
  Swal.fire({
    title: 'Loading...',
    text: 'Fetching shipment details...',
    showConfirmButton: false,
    didOpen: () => {
      Swal.showLoading();

      fetch(`${window.context}/api/order-details/${productId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log(data); // Kiểm tra dữ liệu nhận được

        // Kiểm tra dữ liệu trước khi sử dụng
        if (Array.isArray(data) && data.length > 0) {
          // Xây dựng nội dung modal từ danh sách các mục
          let itemsHtml = '';
          data.forEach(item => {
            const dateIn = new Date(item.dateIn.year,
                item.dateIn.monthValue - 1, item.dateIn.dayOfMonth,
                item.dateIn.hour, item.dateIn.minute, item.dateIn.second);
            const formattedDateIn = dateIn.toLocaleDateString() + ' '
                + dateIn.toLocaleTimeString();
            itemsHtml += `
                   <div class="item cart-item flex">
                    <input style="cursor: pointer; width: 30px; height: 30px" type="checkbox" id="${item.id}" value="${item.id}">
                    <img style="width: 60px; height: 60px" src="${window.context}/static/images/accountPicture.png"
                                     alt="profileImg">
                     <div>      
                     <p>Ten san pham:</p>
                     <span>${item.productName}</span>
                     </div>
                     <div>
                     <p>Ngay nhap</p>
                     <span>${formattedDateIn}</span>
                     </div>
                     <div>
                     <div>
                    <span>So luong</span>
                    <input type="number" min="1" style="width: 60px;">
                    <span>Ton kho</span>
                    <span>${item.quantity}</span>
                  </div>
                      </div>
                     
                      
                    </div>
              `;
          });

          Swal.fire({
            title: 'Chọn lô hàng',
            html:
                `
            <div class="custom-swal-content" style="padding: 20px;">
                ${itemsHtml}
            </div>
            `
            ,
            confirmButtonText: 'OK',
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No shipment details found.',
          });
        }
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error); // Ghi lỗi
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Failed to fetch shipment details.',
        });
      });
    }
  });
}

$('.btn-get-product').on('click', function () {
  Swal.fire({
    title: 'Custom Modal',
    html:
        `
            <div style="padding: 20px;">
            <h2>Modal Header</h2>
            <p>This is a simple custom modal with SweetAlert2.</p>
            </div>
            `
    ,
    confirmButtonText: 'OK',
    customClass: {
      container: 'custom-swal-container'
    }
  });
})
