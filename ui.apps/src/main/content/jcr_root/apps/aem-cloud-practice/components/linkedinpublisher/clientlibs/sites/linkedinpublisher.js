(function ($, document) {

    "use strict";

    $(document).ready(function () {

        const select = $("#linkedin-template-select");
        const preview = $("#linkedin-content-preview");
        const submitBtn = $("#linkedin-submit-btn");
        const selectedImageInput = $("#selected-image");

        function validateForm() {

            const templateSelected =
                select.val() !== "";

            submitBtn.prop(
                "disabled",
                !templateSelected
            );
        }

        select.on("change", function () {

            const content =
                $(this)
                .find(":selected")
                .data("content");

            if (content) {
                preview.html(content);
            } else {
                preview.html(
                    "Select a template"
                );
            }

            validateForm();
        });

        $(".linkedin-image-option").on(
            "click",
            function () {

                $(".linkedin-image-option")
                    .removeClass("selected");

                $(this)
                    .addClass("selected");

                selectedImageInput.val(
                    $(this).data("image")
                );
            });

        submitBtn.on(
            "click",
            function () {

                const payload = {
                    content: preview.html(),
                    imagePath:
                        selectedImageInput.val()
                };

                $.ajax({
                    url: "/bin/linkedin/post",
                    type: "POST",
                    contentType:
                        "application/json",
                    data:
                        JSON.stringify(payload),

                    success: function () {

                        alert(
                            "Successfully posted to LinkedIn"
                        );
                    },

                    error: function () {

                        alert(
                            "LinkedIn publish failed"
                        );
                    }
                });

            });

    });

})(Granite.$, document);