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
  myVar = setTimeout(showPage, 800);
}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}

$('.tab').on('click', function () {
  $('.tab').removeClass('active');
  $(this).addClass('active');
});

var tableAddNewShipment = new DataTable('#table-add-shipment', {
  searching: false,
  bDeferRender: true,
  searchDelay: 500,
  ajax: 'product-list.json',
  serverSide: true,
  processing: true,

  columns: [
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="check-product">  
                    <input type="checkbox" value="${row.productCode}">
                </div>`
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="image-table-product flex">  
                    <img src="${row.image}"> 
                    <div class="product-name">
                        <span>${row.productName}</span>
                    </div>
                </div>`
      },
      width: "20%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="sku-code">  
                    <span>${row.productCode}</span>
                </div>`
      },
      width: "5%"
    }, {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="supplier">  
                   <span>${row.supplier}</span>
                </div>`
      },
      width: "35%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="quantity-product">  
                    <span>${row.stockQuantity}</span>
                </div>`
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        let  statusClass;

        if(row.status === 'Đã ẩn') {
          statusClass = 'lock';
        } else {
          statusClass = 'unlock';
        }
        return `<div class="status-product ${statusClass}">  
                    <span>${row.status}</span>
                </div>`
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
                          <button class="btn-hide">
                            <span><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path d="M38.8 5.1C28.4-3.1 13.3-1.2 5.1 9.2S-1.2 34.7 9.2 42.9l592 464c10.4 8.2 25.5 6.3 33.7-4.1s6.3-25.5-4.1-33.7L525.6 386.7c39.6-40.6 66.4-86.1 79.9-118.4c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C465.5 68.8 400.8 32 320 32c-68.2 0-125 26.3-169.3 60.8L38.8 5.1zM223.1 149.5C248.6 126.2 282.7 112 320 112c79.5 0 144 64.5 144 144c0 24.9-6.3 48.3-17.4 68.7L408 294.5c8.4-19.3 10.6-41.4 4.8-63.3c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3c0 10.2-2.4 19.8-6.6 28.3l-90.3-70.8zM373 389.9c-16.4 6.5-34.3 10.1-53 10.1c-79.5 0-144-64.5-144-144c0-6.9 .5-13.6 1.4-20.2L83.1 161.5C60.3 191.2 44 220.8 34.5 243.7c-3.3 7.9-3.3 16.7 0 24.6c14.9 35.7 46.2 87.7 93 131.1C174.5 443.2 239.2 480 320 480c47.8 0 89.9-12.9 126.2-32.5L373 389.9z"/></svg></span>
                          </button>
                      </div>`;
      },
      width: "15%"
    },
  ]
})
