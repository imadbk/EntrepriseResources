  
  // When the browser is ready...
  $(function() {
  
    // Setup form validation on the #register-form element
    $("#user-form").validate({
    
        // Specify the validation rules
        rules: {
        	username: {
        		required : true,
        		 remote: {
                     url: "checkUser",
                     type: "post"
                  }
        		},
            firstname: "required",
            lastname: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            },
            c_password: { 
                required: true, equalTo: "#inputPassword", minlength: 5
            }, 
            roles : { valueNotEquals: "default" }

        },
        
        // Specify the validation error messages
        messages: {
            username :{ 
            	required : "Un nom d'utilisateur est requis",
            	remote : "nom utilisateur existant"	
            },
            firstname: "Un prénom est requis",
            lastname: "Un nom est requis",
            password: {
                required: "Mot de passe requis",
                minlength: "le mot de passe doit être superieur à 8 caractères"
            },
            c_password: {
                required: "confirmation du Mot de passe requise",
                equalTo: "La confirmation et le mot de passe sont déffirents"
            },
            email: "Merci d'insérer une adresse mail valide ",
            roles: { valueNotEquals: "Choisissez au moins un role" }
        },
        
        submitHandler: function(form) {
            form.submit();
        },

        errorElement: "div",
        wrapper: "div",  // a wrapper around the error message
        errorPlacement: function(error, element) {
            offset = element.offset();
            error.insertAfter(element)
         //   error.addClass('message');  // add a class to the wrapper
            error.css('color', 'red');

        }
    });

  });
  
//add the rule here
  $.validator.addMethod("valueNotEquals", function(value, element, arg){
   return null != value;
  }, "Value must not equal arg.");


  