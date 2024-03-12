import styled from 'styled-components/native';

export const Fieldset = styled.View`
  width: 100%;
  margin-bottom: 25px;
`;

export const WrapDropdown = styled.View`
  margin: 0;
`;

export const Subtitle = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
  margin-top: 25px;
`;

export const AlertTextWrapper = styled.View`
  padding: 0 30px;
`;

export const ContactsWrapper = styled.View`
  padding-bottom: 70px;
`;
