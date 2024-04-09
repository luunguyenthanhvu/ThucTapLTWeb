'use strict';

const api_key = "899244476586798";
FilePond.registerPlugin(
    FilePondPluginImagePreview,
    FilePondPluginImageExifOrientation,
    FilePondPluginFileValidateSize,
    FilePondPluginImageEdit,
    FilePondPluginImageCrop,
    FilePondPluginImageTransform,
    FilePondPluginImageFilter
);

// Select the file input and use
// create() to turn it into a pond
FilePond.create(
    document.querySelector('#upfileAnh'),
    {
      allowMultiple: true,
      acceptedFileTypes: ['image/*'],
      maxFiles: 5,
      instantUpload: true,
      labelIdle: 'Kéo và thả hoặc click để chọn tệp',
      stylePanelLayout: 'stacked',
      styleButtonProcessItemPosition: 'right'
    }
);

let imgList = [];

FilePond.setOptions({
  server: {
    process: async (fieldName, file, metadata, load, error, progress, abort, transfer,
        options) => {
      try {
        let public_id;
        const signatureResponse = await axios.get(
            `${window.context}/cloudinary/get-signature`);

        const formData = new FormData();
        formData.append("file", file);
        formData.append("api_key", api_key);
        formData.append("signature", signatureResponse.data.signature);
        formData.append("timestamp", signatureResponse.data.timestamp);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'https://api.cloudinary.com/v1_1/dter3mlpl/image/upload');
        xhr.upload.onprogress = (event) => {
          console.log(event.loaded / event.total); // Log the progress
          const progressPercentage = Math.round((event.loaded / event.total) * 100);
          progress(progressPercentage);
        };
        xhr.onload = () => {
          if (xhr.status >= 200 && xhr.status < 300) {
            load(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            public_id = response.public_id;
            imgList.push({
              public_id: response.public_id,
              url: response.url
            });
            FilePond.setOptions({
              fileMetadata: {
                [file.id]: {
                  fileId: public_id
                }
              }
            });
            load(public_id);
          } else {
            error('Upload error');
          }
        };
        xhr.onerror = () => {
          error('Upload error');
        };

        xhr.send(formData);

        // Return a function to handle cancellation
        return {
          abort: () => {
            xhr.abort();
            abort();
          }
        };
      } catch (err) {
        console.error(err);
        error('Error occurred during upload');
      }
    },
    revert: (source, load, error) => {
      removeImage(source);
      const doDelete = async function() {
        axios.get(`${window.context}/cloudinary/remove-image`, {
          params: {id: source},
        }).then((response) => {
          console.log(response)
          load('')
        });
      }
      doDelete();
    },
  }
});

function removeImage(public_id) {
  imgList = imgList.filter((image) => image.public_id !== public_id);
}


CKEDITOR.replace('editor');
let arrow = document.querySelectorAll(".arrow");
for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e) => {
    let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
    arrowParent.classList.toggle("showMenu");
  });
}
let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});

var myVar;
function myFunction() {
  myVar = setTimeout(showPage, 5);
}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}

// validate for input
var tenSP = document.getElementById("ten_sp");
var moTaSP = CKEDITOR.instances.editor;
var giaTienSP = document.getElementById("giatien_sp");
var khoiLuongSP = document.getElementById("kl_sp");
var kgMacDinhSP = document.getElementById("kgMacDinh_sp");
var nhaCC = document.getElementById("provider_product");
var ngayHetHan = document.getElementById("expired_day");
var upfileAnh = document.getElementById("upfileAnh");

function validateTenSP() {
  var text = tenSP.value;
  var kyTuHopLe = /^[\p{L}\s']+$/u;
  var error = document.getElementById("ten_sp_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng nhập vào tên sản phầm";
    error.style.display = "block";
    return false;
  } else if (!kyTuHopLe.test(text)) {
    error.textContent = "Tên trái cây chỉ chứa ký tự chữ cái, khoảng trắng.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateNhaCC() {
  var text = nhaCC.value;
  var error = document.getElementById("provider_product_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng chọn nhà cung cấp";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateNgayHetHan() {
  var inputNgayHetHan = document.getElementById("expired_day");
  var ngayHetHan = new Date(inputNgayHetHan.value);
  var now = new Date();

  var error = document.getElementById("expired_day_error");

  // Kiểm tra xem ngày hết hạn đã chọn hay chưa
  if (isNaN(ngayHetHan.getTime())) {
    error.textContent = "Vui lòng chọn ngày hết hạn.";
    error.style.display = "block";
    return false;
  }

  // Kiểm tra xem ngày hết hạn có sau ngày hiện tại không
  if (ngayHetHan <= now) {
    error.textContent = "Ngày hết hạn phải sau ngày hiện tại.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateFileUpload() {
  var inputUploadFile = document.getElementById("upfileAnh");
  var error = document.getElementById("upfileAnh_error");

  // Kiểm tra xem người dùng đã chọn file ảnh hay chưa
  if (imgList.length === 0) {
    error.textContent = "Vui lòng chọn file ảnh.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateKhoiLuongSP() {
  var text = khoiLuongSP.value;
  var error = document.getElementById("kl_sp_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng nhập vào khối lượng nhập hàng.";
    error.style.display = "block";
    return false;
  } else if (isNaN(text) || text <= 0) {
    error.textContent = "Khối lượng nhập hàng chỉ chứa chữ số, không âm.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateKgMacDinhSP() {
  var text = kgMacDinhSP.value;
  var error = document.getElementById("kgMacDinh_sp_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng nhập vào khối lượng mặc định.";
    error.style.display = "block";
    return false;
  } else if (isNaN(text) || text <= 0) {
    error.textContent = "Khối lượng mặc định chỉ chứa chữ số, không âm.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateGiaTienSP() {
  var text = giaTienSP.value;
  var error = document.getElementById("giatien_sp_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng nhập vào giá tiền sản phầm.";
    error.style.display = "block";
    return false;
  } else if (isNaN(text) || text <= 0) {
    error.textContent = "Giá tiền sản phẩm chỉ chứa chữ số, không âm.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}

function validateMoTaSP() {
  var editor = CKEDITOR.instances.editor;
  var error = document.getElementById("mota_sp_error");

  // Kiểm tra xem trình soạn thảo đã khởi tạo chưa
  if (!editor) {
    error.textContent = "Không thể truy cập trình soạn thảo.";
    error.style.display = "block";
    return false;
  }

  // Lấy nội dung từ trình soạn thảo
  var text = editor.getData().trim(); // Loại bỏ khoảng trắng ở đầu và cuối

  // Kiểm tra xem có nội dung không
  if (text.length === 0) {
    error.textContent = "Vui lòng nhập vào mô tả sản phẩm.";
    error.style.display = "block";
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
}


// add event to check input
tenSP.addEventListener("blur", validateTenSP);
// moTaSP.addEventListener("blur", validateMoTaSP);
giaTienSP.addEventListener("blur", validateGiaTienSP);
khoiLuongSP.addEventListener("blur", validateKhoiLuongSP);
kgMacDinhSP.addEventListener("blur", validateKgMacDinhSP);
nhaCC.addEventListener("blur", validateNhaCC);
ngayHetHan.addEventListener("blur", validateNgayHetHan);
upfileAnh.addEventListener("blur", validateFileUpload);

// stop user send post to server
function addNewProduct() {
  const isTenSPValid = validateTenSP();
  const isMoTaSPValid = validateMoTaSP();
  const isGiaTienValid = validateGiaTienSP();
  const isKhoiLuongSPValid = validateKhoiLuongSP();
  const isKgMacDinhSPValid = validateKgMacDinhSP();
  const isNhaCCValid = validateNhaCC();
  const isNgayHetHanValid = validateNgayHetHan();
  const isFileValid = validateFileUpload();

  if (!isTenSPValid || !isMoTaSPValid || !isGiaTienValid || !isKhoiLuongSPValid
      || !isKgMacDinhSPValid || !isNhaCCValid || !isNgayHetHanValid || !isFileValid) {
    console.log(imgList);
  } else {
    const product = {
      name: tenSP.value,
      description: moTaSP.getData(),
      price: giaTienSP.value,
      quantity: khoiLuongSP.value,
      defaultWeight: kgMacDinhSP.value,
      supplier: nhaCC.value,
      expirationDate: ngayHetHan.value,
      img: imgList
    };

    console.log(product);
    // fetch(`${window.context}/admin/product/add-new-product`, {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type' : 'application/json',
    //   },
    //   body: JSON.stringify(product),
    // })
    // .then(response => {
    //   if (!response.ok) {
    //     throw new Error('Network response was not ok');
    //   }
    // })
    // .catch(error => {
    //   console.error('Their is some problem with your fetch operation', error)
    // })
  }
}

