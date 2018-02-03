// Rules
export const REQUIRED = {required: true, message: 'Complete este campo'};
export const REQUIRED_WHITE_SPACE = {required: true, whitespace: true, message: 'Complete este campo'};
export const ONLY_STRING = {
  type: 'string',
  pattern: /^[a-zA-Z][a-zA-Z\s]*$/, 
  message: 'El campo no puede contener numeros, caracteres especiales o iniciar con espacios'
};
export const MAIL = {type: 'email', message: 'Formato de mail no valido'};

export const ruleValidator = (rules) => ({form}) => (mapTo) => (Input) => (
  form.getFieldDecorator(mapTo, {rules})(Input)
);

// Custom Validators
export const RequiredValidator = ruleValidator([REQUIRED]);

export const PasswordValidator = RequiredValidator;

export const MailValidator = ruleValidator([MAIL, REQUIRED_WHITE_SPACE]);

export const DatosPersonalesValidator = ruleValidator([REQUIRED, ONLY_STRING]);

export const FechaValidator = RequiredValidator;


// Custom function validators
export const validateFile = file => (rule, value, cb) => {
  if (!file) {
    cb('Debe subir un archivo');
  }
  cb();
}