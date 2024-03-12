import { TouchableOpacityProps } from 'react-native';

// Style import
import { ButtonContainer, ButtonText } from './styles';
import theme from '@theme/index';

interface Props extends TouchableOpacityProps {
  label: string;
  size?: 'SM' | 'MD' | 'LG' | 'XL' | 'XXL';
  icon?: React.ReactNode;
  bottom?: boolean;
  backgroundColor?: string;
}

export function Button({
  label,
  size = 'MD',
  icon,
  bottom = false,
  backgroundColor = theme.COLORS.PRIMARY,
  ...rest
}: Props) {
  return (
    <ButtonContainer
      {...rest}
      size={size}
      activeOpacity={0.7}
      bottom={bottom}
      backgroundColor={backgroundColor}
    >
      <ButtonText size={size}>{label}</ButtonText>

      {icon && icon}
    </ButtonContainer>
  );
}
