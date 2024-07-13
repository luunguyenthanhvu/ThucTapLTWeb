'use strict';

const api_key = "899244476586798";
FilePond.registerPlugin(FilePondPluginImagePreview,
    FilePondPluginImageExifOrientation, FilePondPluginFileValidateSize,
    FilePondPluginImageEdit, FilePondPluginImageCrop,
    FilePondPluginImageTransform, FilePondPluginImageFilter);

// Select the file input and use
// create() to turn it into a pond
FilePond.create(document.querySelector('#upfileAnh'), {
  allowMultiple: true,
  acceptedFileTypes: ['image/*'],
  maxFiles: 1,
  instantUpload: true,
  labelIdle: 'Chọn ảnh',
  stylePanelLayout: 'stacked',
  styleButtonProcessItemPosition: 'right',
  server: {
    process: async (fieldName, file, metadata, load, error, progress, abort,
        transfer, options) => {
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
        xhr.open('POST',
            'https://api.cloudinary.com/v1_1/dter3mlpl/image/upload');
        xhr.upload.onprogress = (event) => {
          console.log(event.loaded / event.total); // Log the progress
          const progressPercentage = Math.round(
              (event.loaded / event.total) * 100);
          progress(progressPercentage);
        };
        xhr.onload = () => {
          if (xhr.status >= 200 && xhr.status < 300) {
            load(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            public_id = response.public_id;
            imgList.push({
              publicId: response.public_id,
              assetId: response.asset_id,
              url: response.url,
              category: 'main'
            });
            console.log(imgList)
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
    }, revert: (source, load, error) => {
      removeImage(source);
      const doDelete = async function () {
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
FilePond.create(document.querySelector('#upMultiFileImg'), {
  allowMultiple: true,
  acceptedFileTypes: ['image/*'],
  maxFiles: 6,
  instantUpload: true,
  labelIdle: 'Chọn ảnh',
  stylePanelLayout: 'stacked',
  styleButtonProcessItemPosition: 'right',
  server: {
    process: async (fieldName, file, metadata, load, error, progress, abort,
        transfer, options) => {
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
        xhr.open('POST',
            'https://api.cloudinary.com/v1_1/dter3mlpl/image/upload');
        xhr.upload.onprogress = (event) => {
          console.log(event.loaded / event.total); // Log the progress
          const progressPercentage = Math.round(
              (event.loaded / event.total) * 100);
          progress(progressPercentage);
        };
        xhr.onload = () => {
          if (xhr.status >= 200 && xhr.status < 300) {
            load(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            public_id = response.public_id;
            imgList.push({
              publicId: response.public_id,
              assetId: response.asset_id,
              url: response.url,
              category: 'sup'
            });
            console.log(imgList)
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
    }, revert: (source, load, error) => {
      removeImage(source);
      const doDelete = async function () {
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

let imgList = [];

function removeImage(public_id) {
  imgList = imgList.filter((image) => image.publicId !== public_id);
}

CKEDITOR.replace('editor');
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

// validate for input
var tenSP = document.getElementById("ten_sp");
var moTaSP = CKEDITOR.instances.editor;
var giaTienSP = document.getElementById("giatien_sp");
// var khoiLuongSP = document.getElementById("kl_sp");
var kgMacDinhSP = document.getElementById("kgMacDinh_sp");
var nhaCC = document.getElementById("provider_product");
var ngayHetHan = document.getElementById("expired_day");
var upfileAnh = document.getElementById("upfileAnh");
var doanhMuc = document.getElementById("doanhMuc");
// var sourceImport = document.getElementById("sourceImport");
// var driedFruit = document.getElementById("driedFruit");

function validateTenSP() {
  var text = tenSP.value;
  var error = document.getElementById("ten_sp_error");
  if (text.length == 0 || text == null) {
    error.textContent = "Vui lòng nhập vào tên sản phầm";
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
// khoiLuongSP.addEventListener("blur", validateKhoiLuongSP);
kgMacDinhSP.addEventListener("blur", validateKgMacDinhSP);
nhaCC.addEventListener("blur", validateNhaCC);
// ngayHetHan.addEventListener("blur", validateNgayHetHan);
// upfileAnh.addEventListener("blur", validateFileUpload);
$("#submit_product_btn").click(addNewProduct);

// stop user send post to server
function addNewProduct() {
  const isTenSPValid = validateTenSP();
  const isMoTaSPValid = validateMoTaSP();
  const isGiaTienValid = validateGiaTienSP();
  // const isKhoiLuongSPValid = validateKhoiLuongSP();
  const isKgMacDinhSPValid = validateKgMacDinhSP();
  const isNhaCCValid = validateNhaCC();
  // const isNgayHetHanValid = validateNgayHetHan();
  // const isFileValid = validateFileUpload();

  // get info image thumbnail for product
  let mainImages = imgList.filter(image => image.category === 'main').map(
      image => image.url);
  let mainImgPublicId = imgList.filter(image => image.category === 'main').map(
      image => image.publicId);
  let mainImgAssetId = imgList.filter(image => image.category === 'main').map(
      image => image.assetId);
  // get sub img info for product
  const supImages = imgList.filter(image => image.category === 'sup').map(
      image => image.url);
  const supImagesString = supImages.join(',');

  const supImgPublicId = imgList.filter(image => image.category === 'sup').map(
      image => image.publicId);
  const supImgPublicIdStr = supImgPublicId.join(',');

  const supImgAssetId = imgList.filter(image => image.category === 'sup').map(
      image => image.assetId);
  const supImgAssetIdStr = supImgAssetId.join(',');

  Swal.fire({
    title: "Bạn có muốn lưu sản phẩm không?",
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: "Lưu",
    denyButtonText: `Không lưu`,
    cancelButtonText: 'Hủy',
  }).then((result) => {
    if (result.isConfirmed) {
      if (!validateMoTaSP() || !validateGiaTienSP() || !validateNhaCC()
          || !validateKgMacDinhSP()) {
        Swal.fire("Vui Lòng nhập dữ liệu!", "", "warning");
      } else {
        const data = {
          name: tenSP.value,
          description: moTaSP.getData(),
          category: doanhMuc.value,
          // sourceImport: sourceImport.value,
          // driedFruit: driedFruit.value,
          price: giaTienSP.value,
          // quantity: khoiLuongSP.value,
          defaultWeight: kgMacDinhSP.value,
          supplier: nhaCC.value,
          expirationDate: ngayHetHan.value,
          // img: mainImages[0],
          supImages: supImagesString,
          mainImgPublicId: mainImgPublicId[0],
          mainImgAssetId: mainImgAssetId[0],
          supImgPublicId: supImgPublicIdStr,
          supImgAssetId: supImgAssetIdStr
        }
        $.ajax({
          type: 'POST',
          url: `${window.context}/admin/product/add-new-product`,
          data: {
            name: tenSP.value,
            description: moTaSP.getData(),
            category: doanhMuc.value,
            // sourceImport: sourceImport.value,
            // driedFruit: driedFruit.value,
            price: giaTienSP.value,
            // quantity: khoiLuongSP.value,
            defaultWeight: kgMacDinhSP.value,
            supplier: nhaCC.value,
            expirationDate: ngayHetHan.value,
            // img: mainImages[0],
            supImages: supImagesString,
            mainImgPublicId: mainImgPublicId[0],
            mainImgAssetId: mainImgAssetId[0],
            supImgPublicId: supImgPublicIdStr,
            supImgAssetId: supImgAssetIdStr
          },
          success: function (response) {
            console.log(response.message)
            Swal.fire(response.message, "", "success").then(() => {
              window.location.href = `${window.context}/admin/product/add-new-product`;
            });
          },
          error: function (error) {
            console.log(error); // Xem nội dung của error object trong console
            if (error.hasOwnProperty('message')) {
              alert(error.message);
            } else {
              alert("Lỗi không xác định");
            }
          }
        })
      }
    } else if (result.isDenied) {
      Swal.fire("Changes are not saved", "", "info");
    }
  });
}

