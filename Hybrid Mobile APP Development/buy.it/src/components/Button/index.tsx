import { TouchableOpacityProps } from "react-native";

// Style import
import { ButtonContainer, ButtonText } from './styles';

interface Props extends TouchableOpacityProps {
  label: string;
  size?: 'SM' | 'MD' | 'LG' | 'XL' | 'XXL';
  icon?: React.ReactNode;
  bottom?: boolean
}

export function Button({ label, size = 'MD', icon, bottom = false, ...rest }: Props) {
  return (
    <ButtonContainer
      {...rest}
      size={size}
      activeOpacity={0.7}
      bottom={bottom}
    >
      <ButtonText 
        size={size}
      >
        {label}
      </ButtonText>

      {icon && icon}
    </ButtonContainer>
  );
}
