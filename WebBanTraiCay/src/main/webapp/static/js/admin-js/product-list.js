'use strict';
let arrow = document.querySelectorAll(".arrow");
let closeSideBarBtn = document.querySelector(".btn-close-home");

for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e) => {
    let arrowParent = e.target.parentElement.parentElement; // Trở về phần tử cha của mũi tên
    arrowParent.classList.toggle("showMenu");
  });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");

sidebarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");

  // Sau khi toggle sidebar, kiểm tra và điều chỉnh hiển thị nút đóng sidebar
  if (!sidebar.classList.contains("close")) {
    closeSideBarBtn.style.display = "inline-block"; // Hiển thị nút đóng
  } else {
    closeSideBarBtn.style.display = "none"; // Ẩn nút đóng
  }
});

closeSideBarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");
  closeSideBarBtn.style.display = "none"; // Luôn ẩn nút đóng khi click để đóng sidebar
});

var myVar;

function myFunction() {
  myVar = setTimeout(showPage, 600);
}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}

$('.tab').on('click', function () {
  $('.tab').removeClass('active');
  $(this).addClass('active');
});

let selectedProductCodes = [];
let tableAddNewShipment = new DataTable('#table-add-shipment', {
  searching: false,
  bDeferRender: true,
  ajax: {
    url: `${window.context}/api/product-list/get-data-table`,
    contentType: 'application/json',
    type: "POST",
    dataType: "json",
    data: function (d) {
      console.log(d)
      let productName = $('#product-name').val();
      d.searchText = $('#product-name').val();
      d.category = $('#product-category').val();
      return JSON.stringify(d);
    }
  },
  serverSide: true,
  processing: true,
  scrollY: '550px',
  scrollCollapse: true,
  columns: [
    {
      data: undefined,
      render: function (data, type, row) {
        let isChecked = selectedProductCodes.includes(`${row.id}`) ? 'checked'
            : '';
        console.log(isChecked)
        return `<div class="check-product">  
                    <input type="checkbox" value="${row.id}" ${isChecked}>
                </div>`;
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="image-table-product flex">  
                    <img style="width: 80px; height: 80px" data-assets="${row.imgPublicId}" src="${window.context}/static/images/loading-cat.gif"> 
                    <div class="product-name">
                        <span>${row.productName}</span>
                    </div>
                </div>`
      },
      width: "30%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="sku-code"> 
                    <span>${row.category}</span>
                </div>`
      },
      width: "20%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="sku-code">  
                    <span>${row.id}</span>
                </div>`
      },
      width: "5%"
    }, {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="supplier">  
                   <span>${row.provider}</span>
                </div>`
      },
      width: "20%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="quantity-product">  
                    <span>${row.quantityStock}</span>
                </div>`
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        let statusClass;
        let status;

        if (row.status === 1) {
          statusClass = 'btn-show';
          status = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path d="M288 80c-65.2 0-118.8 29.6-159.9 67.7C89.6 183.5 63 226 49.4 256c13.6 30 40.2 72.5 78.6 108.3C169.2 402.4 222.8 432 288 432s118.8-29.6 159.9-67.7C486.4 328.5 513 286 526.6 256c-13.6-30-40.2-72.5-78.6-108.3C406.8 109.6 353.2 80 288 80zM95.4 112.6C142.5 68.8 207.2 32 288 32s145.5 36.8 192.6 80.6c46.8 43.5 78.1 95.4 93 131.1c3.3 7.9 3.3 16.7 0 24.6c-14.9 35.7-46.2 87.7-93 131.1C433.5 443.2 368.8 480 288 480s-145.5-36.8-192.6-80.6C48.6 356 17.3 304 2.5 268.3c-3.3-7.9-3.3-16.7 0-24.6C17.3 208 48.6 156 95.4 112.6zM288 336c44.2 0 80-35.8 80-80s-35.8-80-80-80c-.7 0-1.3 0-2 0c1.3 5.1 2 10.5 2 16c0 35.3-28.7 64-64 64c-5.5 0-10.9-.7-16-2c0 .7 0 1.3 0 2c0 44.2 35.8 80 80 80zm0-208a128 128 0 1 1 0 256 128 128 0 1 1 0-256z"/></svg>'
        } else {
          statusClass = 'btn-hide';
          status = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path d="M38.8 5.1C28.4-3.1 13.3-1.2 5.1 9.2S-1.2 34.7 9.2 42.9l592 464c10.4 8.2 25.5 6.3 33.7-4.1s6.3-25.5-4.1-33.7L525.6 386.7c39.6-40.6 66.4-86.1 79.9-118.4c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C465.5 68.8 400.8 32 320 32c-68.2 0-125 26.3-169.3 60.8L38.8 5.1zM223.1 149.5C248.6 126.2 282.7 112 320 112c79.5 0 144 64.5 144 144c0 24.9-6.3 48.3-17.4 68.7L408 294.5c8.4-19.3 10.6-41.4 4.8-63.3c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3c0 10.2-2.4 19.8-6.6 28.3l-90.3-70.8zM373 389.9c-16.4 6.5-34.3 10.1-53 10.1c-79.5 0-144-64.5-144-144c0-6.9 .5-13.6 1.4-20.2L83.1 161.5C60.3 191.2 44 220.8 34.5 243.7c-3.3 7.9-3.3 16.7 0 24.6c14.9 35.7 46.2 87.7 93 131.1C174.5 443.2 239.2 480 320 480c47.8 0 89.9-12.9 126.2-32.5L373 389.9z"/></svg>'
        }
        2
        return `<div class="actions">  
                          <button value="${row.id}" class="${statusClass}" data-action="block-product">
                            ${status}
                          </button>
                      </div>`;
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="actions">  
                          <button class="btn-edit">
                             <span> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg></span>
                          </button>
                      </div>`;
      },
      width: "10%"
    },
  ],
  drawCallback: function () {
    setTimeout(() => {
      console.log(tableAddNewShipment.rows())
      tableAddNewShipment.columns.adjust()
    }, 1500)
  }
})
$("#table-add-shipment").on('change', '.check-product input[type="checkbox"]',
    function () {
      if (!selectedProductCodes.includes($(this).val())) {
        selectedProductCodes.push($(this).val());
      } else {
        selectedProductCodes = [...selectedProductCodes.filter(
            code => code !== $(this).val())];
      }
    })
// hide product
$('#table-add-shipment').on('click', 'button[data-action="block-product"]',
    function () {
      Swal.fire({
        title: "Ẩn sản phẩm",
        text: "Bạn chắc chắn muốn ẩn sản phẩm này?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Chắc chắn!",
        denyButtonText: "Không!",
      }).then((result) => {
        console.log(result)
        console.log($(this).val())
        if (result.isConfirmed) {
          console.log("ajax ne")
          $.ajax({
            url: `${window.context}/api/product-details/update-status`,
            type: 'POST',
            data: JSON.stringify({
              id: $(this).val()
            }),
            success: function (response) {
              console.log(response)
              Swal.fire({
                title: "Thành công",
                text: "Ẩn sản phẩm thành công!",
                icon: "success"
              })
              tableAddNewShipment.ajax.reload();
            },
            error: function (xhr, status, error) {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: xhr.responseText,
              });
            }
          })
        } else if (result.isDenied) {
        }
      });
    })
$('#table-add-shipment').on('change', 'input[type="checkbox"]', function () {
  const isChecked = $(this).prop('checked');
  const productCode = $(this).val();
  if (isChecked) {
    quantitySelected.quantity = quantitySelected.quantity + 1;
  } else {
    quantitySelected.quantity = --quantitySelected.quantity;
  }
  $('#selectedProductCount').text(quantitySelected.quantity);
})
let quantitySelected;
quantitySelected = {
  aInternal: 0,
  aListener: function (val) {
  },
  set quantity(val) {
    this.aInternal = val;
    this.aListener(val);
  },
  get quantity() {
    return this.aInternal;
  },
  registerListener: function (listener) {
    this.aListener = listener;
  }
}

function increaseSelectedProduct(checkbox) {
  let productCode = checkbox.value;
  if (checkbox.checked) {
    if (!selectedProductCodes.includes(productCode)) {
      selectedProductCodes.push(productCode);
    } else {
      selectedProductCodes = [...selectedProductCodes.filter(
          code => code !== productCode)];
    }
  }
}

$('#selectedProductCount').text(quantitySelected.quantity);

$('.search-btn').on('click', function () {
  tableAddNewShipment.ajax.reload();
})

$('.reset-btn').on('click', function () {
  $('#product-name').val('');
  tableAddNewShipment.ajax.reload();
})

tableAddNewShipment.on('draw.dt', function () {
  var cloudName = 'dter3mlpl';
  var apiKey = '899244476586798';
  var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

  $('tbody tr td .image-table-product.flex img').each((_, elements) => {
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

});

$('#btn-add-new-product').on('click', function () {
  window.location.href = `${window.context}/admin/product/add-new-product`;
})

$('#btn-add-new-shipment').on('click', function () {
  if (selectedProductCodes.length === 0) {
    Swal.fire({
      title: "Vui Lòng chọn ít nhất 1 sản phẩm",
      icon: "warning"
    })
  } else {
    const swalLoading = Swal.fire({
      title: 'Loading...',
      text: 'Please wait while we process your request.',
      allowOutsideClick: false, // Ngăn người dùng đóng modal bằng cách nhấp ra ngoài
      didOpen: () => {
        Swal.showLoading(); // Hiển thị spinner loading
      }
    });

    // Dữ liệu cần gửi
    let listProductCodes = selectedProductCodes.map(Number);

    // Thực hiện yêu cầu AJAX
    $.ajax({
      url: `${window.context}/api/shipments-api/add-new-shipments`,
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(listProductCodes),
      success: function (response) {
        setTimeout(() => {
          // Ẩn modal loading
          swalLoading.close();

          // Chuyển hướng sau khi thành công
          window.location.href = `${window.context}/admin/shipment/add-new-shipments`;
        }, 1000)
      },
      error: function (xhr, status, error) {
        // Ẩn modal loading
        swalLoading.close();

        // Hiển thị lỗi
        console.log(xhr);
        console.log(status);
        console.log(error);

        Swal.fire({
          title: 'Error!',
          text: 'Something went wrong. Please try again.',
          icon: 'error'
        });
      }
    });
  }
})



