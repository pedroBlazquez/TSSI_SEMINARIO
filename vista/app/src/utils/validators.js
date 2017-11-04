// Rules
export const REQUIRED = {required: true, message: 'Complete este campo'};
export const REQUIRED_WHITE_SPACE = {required: true, whitespace: true, message: 'Complete este campo'};
export const MAIL = {type: 'email', message: 'Formato de mail no valido'};

export const ruleValidator = (rules) => ({form}) => (mapTo) => (Input) => (
  form.getFieldDecorator(mapTo, {rules})(Input)
);

// Custom Validators
export const PasswordValidator = ruleValidator([REQUIRED]);

export const MailValidator = ruleValidator([MAIL, REQUIRED_WHITE_SPACE]);

export const DatosPersonalesValidator = ruleValidator([REQUIRED_WHITE_SPACE]);