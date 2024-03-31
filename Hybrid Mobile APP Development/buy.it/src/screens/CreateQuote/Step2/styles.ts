import styled from 'styled-components/native';

export const QuantityContainer = styled.View`
  flex-direction: column;
`;

export const InputWrapper = styled.View`
  flex: 1;
`;

export const QuantityButton = styled.TouchableOpacity.attrs({
  activeOpacity: 0.7,
})`
  display: flex;
  align-items: center;
  justify-content: center;

  padding: 14px 12px;
  background-color: ${({ theme }) => theme.COLORS.PRIMARY};

  border-radius: 4px;

  height: 56px;
  width: 45px;
`;

export const InputLabel = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin-bottom: 12px;

  text-align: left;
`;
