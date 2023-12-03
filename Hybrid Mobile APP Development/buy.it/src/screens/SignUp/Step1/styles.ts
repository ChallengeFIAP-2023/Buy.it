import styled, { css } from "styled-components/native";

interface OptionProps {
  active?: boolean;
}

export const Container = styled.KeyboardAvoidingView`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Content = styled.View`
  padding: 0 30px;
  flex: 1;
  justify-content: start;
`;

export const Option = styled.Pressable<OptionProps>`
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};
  border: 2px solid ${({ theme, active }) => active ? theme.COLORS.PRIMARY : theme.COLORS.GRAY_600};
  border-radius: 7px;
  padding: 25px 16px;
  margin: 15px 0;
`;

export const OptionText = styled.Text<OptionProps>`
  color: ${({ theme, active }) => active ? theme.COLORS.WHITE : theme.COLORS.GRAY_300};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
`

// export const AlertText = styled.Text<OptionProps>`
//   color: ${({ theme, active }) => active ? theme.COLORS.WHITE : theme.COLORS.GRAY_300};
//   font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
//   font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;

//   margin-bottom: 30px;
// `
