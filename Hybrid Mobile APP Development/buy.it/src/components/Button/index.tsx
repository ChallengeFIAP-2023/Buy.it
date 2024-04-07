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
  type?: 'primary' | 'secondary'
  iconFirst?: boolean;
}

export function Button({
  label,
  size = 'MD',
  icon,
  bottom = false,
  backgroundColor = theme.COLORS.PRIMARY,
  type = 'primary',
  iconFirst = false,
  ...rest
}: Props) {
  return (
    <ButtonContainer
      {...rest}
      size={size}
      activeOpacity={0.7}
      bottom={bottom}
      backgroundColor={backgroundColor}
      type={type}
      iconFirst={iconFirst}
    >
      <ButtonText size={size} type={type}>{label}</ButtonText>
      {icon && icon}
    </ButtonContainer>
  );
}
