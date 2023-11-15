import { TouchableOpacityProps } from "react-native";

// Style import
import { ButtonContainer, ButtonText } from './styles';

interface Props extends TouchableOpacityProps {
  label: string;
}

export function Button({ label, ...rest }: Props) {
  return (
    <ButtonContainer
      {...rest}
      activeOpacity={0.7}
    >
      <ButtonText>{label}</ButtonText>
    </ButtonContainer>
  );
}
