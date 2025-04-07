function showResult(message, isSuccess = true) {
  const $result = $("#result");
  $result
  .removeClass("text-success text-danger")
  .addClass(isSuccess ? "text-success" : "text-danger")
  .text(message)
  .show()
  .delay(2000)
  .fadeOut();
}