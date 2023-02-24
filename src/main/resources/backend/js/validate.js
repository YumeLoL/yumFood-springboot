
function isValidUsername (str) {
  return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

// User account validation
function checkUserName (rule, value, callback){
  if (value === "") {
    callback(new Error("Please input user account"))
  } else if (value.length > 20 || value.length <3) {
    callback(new Error("Account length should between 3-20"))
  } else {
    callback()
  }
}

// Name validation
function checkName (rule, value, callback){
  if (value === "") {
    callback(new Error("Please input user name"))
  } else if (value.length > 12) {
    callback(new Error("Name length should between 1-12"))
  } else {
    callback()
  }
}

// Check if is a valid Australian phone number
function isCellPhone (number) {
  // Remove all non-digit characters from the number
  const cleanedNumber = number.toString().replace(/\D/g, '');
  console.log(cleanedNumber);
  // Check if the cleaned number matches the Australian cellphone number pattern
  const regex = /^(4|04|\+614)[0-9]{8}$/;
  return regex.test(cleanedNumber);
}

// phone validation
function checkPhone (rule, value, callback){
  if (value === "") {
    callback(new Error("Please input contact number"))
  } else if (!isCellPhone(value)) {
    callback(new Error("Please input a correct number!"))
  } else {
    callback()
  }
}

// Check if is a valid email address
function isValidEmail(email) {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
}

// email validation
function checkEmail (rule, value, callback){
  if (value === "") {
    callback(new Error("Please input email"))
  } else if (!isValidEmail(value)) {
    callback(new Error("Please input a correct email!"))
  } else {
    callback()
  }
}