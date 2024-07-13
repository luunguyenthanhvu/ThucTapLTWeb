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

let table = new DataTable('#table-update-inventory', {
  searching: false,
  deferRender: true,
  searchDelay: 500,
  columnDefs: [
    {targets: '_all', className: 'dt-body-left'}
  ]
});

function renderTable(date) {
  // Clear existing data
  table.clear();

  // Dummy data - Replace with your actual data fetching logic
  const data = {
    '04/2024': [
      ["22 - 04 - 2024", "Excel", "file1.xlsx", "Hoàn tất", "Download"],
      ["20 - 04 - 2024", "Excel", "file2.xlsx", "Hoàn tất", "Download"]
    ]
  };

  // Check if data for the selected date exists
  if (data[date]) {
    // Add new data
    table.rows.add(data[date]);
  } else {
    // If no data exists, add a row for displaying a message
    table.row.add(["", "Không có dữ liệu", "", "", ""]).draw();
  }
}

// Default render for the first tab
renderTable('04/2024');

