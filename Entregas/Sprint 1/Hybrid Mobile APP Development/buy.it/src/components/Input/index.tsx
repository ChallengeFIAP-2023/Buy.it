import { Fragment } from "react";
import { TextInputProps } from "react-native";

// Theme import
import theme from "@theme/index";

// Style import
import { Label, InputContainer } from './styles';

// Interface
interface Props extends TextInputProps {
  label?: string;
};

export function Input({ label, ...rest }: Props) {
  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      <InputContainer
        {...rest}
        placeholderTextColor={theme.COLORS.GRAY_200}
        underlineColorAndroid="transparent"
      />
    </Fragment>
  );
}

