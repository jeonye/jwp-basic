// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".form-delete button[type=submit]").click(deleteAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var question = json.question;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId, question.questionId);
  $(".qna-comment-slipp-articles").prepend(template);

  // 답변 개수 갱신
  $("#countOfComment strong").text(question.countOfComment);
}

function onError(xhr, status) {
  alert("error");
}

function deleteAnswer(e) {
  e.preventDefault();

  var article = $(this);
  var queryString = article.closest('form').serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : function(json, status) {
      article.closest('article').remove();

      // 답변 개수 갱신
      $("#countOfComment strong").text(json.question.countOfComment);
    }
  });
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};